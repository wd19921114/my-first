package cn.ppz111.my.handler;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yuki
 */
@Component
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class Task {
    private final StringRedisTemplate redisTemplate;
    /**
     * 生命值
     */

}
