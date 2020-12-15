package cn.ppz111.my.utils;

import cn.ppz111.my.exception.CgErrorException;
import cn.ppz111.my.viewentity.user.AdminLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Yuki
 */
@Component
@Slf4j
public class AdminUtil {
    private static StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        AdminUtil.redisTemplate = redisTemplate;
    }
    /**
     * admin
     */
    public static AdminLoginVO getAdmin(){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= Objects.requireNonNull(attributes).getRequest();
        String token = request.getHeader("Authorization").substring(7);
        AdminLoginVO adminLoginVO = JwtUtil.checkToken(token);
        if(ObjectUtils.isEmpty(adminLoginVO)){
            throw new CgErrorException(400,"未查询到");
        }
        return adminLoginVO;

    }
}
