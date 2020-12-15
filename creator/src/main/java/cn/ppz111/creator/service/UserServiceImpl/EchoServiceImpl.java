package cn.ppz111.creator.service.UserServiceImpl;

import cn.ppz111.creator.service.EchoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Yuki
 */
@Service
@AllArgsConstructor
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String string) {
        return "welcome --" + string;
    }

    @Override
    public String echoDubboMix(int id) {
        System.out.println("id:"+id);
        return null;
    }

    @Override
    public String echoMQConsumer(String str) {
        System.out.println("订阅消息:"+str);
        return null;
    }
}
