package cn.ppz111.my.utils;

import cn.ppz111.my.constant.RedisConstant;
import cn.ppz111.my.exception.CgErrorException;
import cn.ppz111.my.viewentity.LoginResultVO;
import cn.ppz111.my.viewentity.user.AdminLoginVO;
import com.alibaba.fastjson.JSON;
import cn.ppz111.my.enums.UserStatusEnum;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2019-07-10
 */
@Component
public class JwtUtil {

    private final static String SECRET_KEY = "MyApp";

    private final static long TOKEN_EXP = 1000 * 60 * 60 * 24 * 30L;

    private final static long ERROR_TIME = 3000;

    private static StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate){
        JwtUtil.redisTemplate=redisTemplate;
    }

    public static String getToken(String id) {
        return Jwts.builder()
                .setSubject(id).claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public static AdminLoginVO checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            System.out.println(claims.getSubject());
            String bool = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,claims.getSubject()));
            if (StringUtils.isEmpty(bool)) {
                throw new CgErrorException(10086,"登录信息过期，请重新登录");
            }
            UserStatusEnum status=JSON.parseObject(bool, LoginResultVO.class).getStatus();
            int mark=2;
            if (status.getCode()%mark!=0){
                throw new CgErrorException(10085,"该账号已冻结");
            }
            return JSON.parseObject(bool,AdminLoginVO.class);
        } catch (ExpiredJwtException |SignatureException e1) {
            throw new CgErrorException(10086,"登录信息过期，请重新登录");
        }

    }
}
