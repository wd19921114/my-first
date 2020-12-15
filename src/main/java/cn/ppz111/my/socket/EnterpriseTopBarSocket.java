package cn.ppz111.my.socket;

import cn.ppz111.my.viewentity.user.AdminLoginVO;
import cn.ppz111.my.viewentity.user.IndexTopBarVO;
import com.alibaba.fastjson.JSON;
import cn.ppz111.my.entity.MyUser;
import cn.ppz111.my.mapper.UserMapper;
import cn.ppz111.my.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author : Yuki
 * @date : 2020/6/24
 */
@ServerEndpoint("/ws/topBar/{token}")
@Component
@Slf4j
public class EnterpriseTopBarSocket {
    private static UserMapper userMapper;
    private static ConcurrentHashMap<Long, EnterpriseTopBarSocket> links = new ConcurrentHashMap<>();
    private Session session;
    private Long userId;
    private String memberLevel;

    @Autowired
    private void setEnter(UserMapper userMapper) {
        EnterpriseTopBarSocket.userMapper = userMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        this.session = session;
        AdminLoginVO info = JwtUtil.checkToken(token);
        Long userId = info.getUserId();
        this.userId = userId;
        links.put(userId, this);
        sendMessage();
        log.info("成功建立连接");
    }

    @OnClose
    public void onClose() {
        links.remove(this.userId);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到消息:" + message);
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage() {
        try {
            IndexTopBarVO topBarVO = new IndexTopBarVO();
            MyUser user = userMapper.selectById(this.userId);
            System.out.println(this.userId);
            topBarVO.setAvatarUrl(user.getAvatarUrl());
            topBarVO.setNickname(user.getNickname());
            topBarVO.setMemberLevel(this.memberLevel);
            //获取未读消息数
            topBarVO.setMsgNum(1);
            this.session.getBasicRemote().sendText(JSON.toJSONString(topBarVO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送消息
     *
     * @param userId 用户id
     */
    public static void sendMsgToUser(Long userId) {
        if (EnterpriseTopBarSocket.links.containsKey(userId)) {
            EnterpriseTopBarSocket.links.get(userId).sendMessage();
        }
    }
}
