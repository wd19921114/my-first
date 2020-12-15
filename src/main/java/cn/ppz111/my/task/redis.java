package cn.ppz111.my.task;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @author Yuki
 */
@AllArgsConstructor
public class redis {
    private final StringRedisTemplate redisTemplate;
    @Async
    @Scheduled(cron="30 * * * * ?")
    void getRedis(){
        redisTemplate.opsForValue().setIfAbsent("heart","1",60, TimeUnit.SECONDS);
    }
}
