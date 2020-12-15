package cn.ppz111.my.service.impl;

import cn.ppz111.my.entity.PraisePost;
import cn.ppz111.my.mapper.PraisePostMapper;
import cn.ppz111.my.utils.RabbitUtil;
import cn.ppz111.my.viewentity.post.PostVO;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ppz111.my.entity.MyPost;
import cn.ppz111.my.mapper.PostMapper;
import cn.ppz111.my.service.PostService;
import cn.ppz111.my.utils.AdminUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Yuki
 */
@Service
@AllArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, MyPost> implements PostService {
    private final StringRedisTemplate redisTemplate;
    private final PraisePostMapper praisePostMapper;



    @Override
    public void addPost(String postName,String postUrl,String postDetail) {
        MyPost post=new MyPost();
        post.setUserId(AdminUtil.getAdmin().getUserId());
        post.setCreateTime(new Date());
        post.setPostName(postName);
        post.setStatus(0);
        post.setPostUrl(postUrl);
        baseMapper.insert(post);
    }

    @Override
    public List<PostVO> postList() {
        return baseMapper.postList(AdminUtil.getAdmin().getUserId());
    }

    @Override
    public Object login(int a) {
        RabbitUtil.sendMq("消息队列的消息在登陆接口发送");
        RabbitUtil.sendLateMq("消息队列的消息在登陆接口发送,这是个延迟的消息",10000L);
        return "over";
    }

    @Override
    public List<String> hotList() {
        redisTemplate.opsForZSet().add("hot","ig",1.0);
        redisTemplate.opsForZSet().add("hot","omg",2.0);
        redisTemplate.opsForZSet().add("hot","rng",3.0);
        printZSet("hot");
        return null;
    }

    @Override
    public Integer praisePost(Long postId) {
        PraisePost praisePost = praisePostMapper.selectOne(Wrappers.<PraisePost>lambdaQuery().eq(PraisePost::getPostId, postId).eq(PraisePost::getUserId, AdminUtil.getAdmin().getUserId()));
        if(ObjectUtils.isEmpty(praisePost)){
            praisePost=new PraisePost();
            praisePost.setPostId(postId);
            praisePost.setUserId(AdminUtil.getAdmin().getUserId());
            praisePostMapper.insert(praisePost);
            return 1;
        }else {
            praisePostMapper.deleteById(praisePost.getId());
            return 0;
        }
    }

    private void printZSet(String key) {
        //按照排名先后(从小到大)打印指定区间内的元素, -1为打印全部
        Set<String> range = redisTemplate.opsForZSet().range(key, 0, -1);
        //reverseRange 从大到小
        System.out.println(range);
    }
    private String tudo() {
        String number = redisTemplate.opsForValue().get("number");
        if(StringUtils.isBlank(number)){
                redisTemplate.opsForValue().set("number", String.valueOf(Integer.valueOf((int) (Math.random()*10+1))));

        }
        return number;
    }
}
