package cn.ppz111.my.handler;

import cn.ppz111.my.exception.CgErrorException;
import cn.ppz111.my.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2019-07-10
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String BEARER = "Bearer";

    private static final Integer VALUE = 7;

    /**
     *         Enumeration<?> enum1 = request.getHeaderNames();
     *         while (enum1.hasMoreElements()) {
     *             String key = (String) enum1.nextElement();
     *             String value = request.getHeader(key);
     *             System.out.println(key + "\t" + value);
     *         }
     *         System.out.println(authHeader);
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler){

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            throw new CgErrorException(10086,"用户未登录");
        }

        //取得token

        if (StringUtils.isEmpty(authHeader) || authHeader.length() < VALUE) {
            throw new CgErrorException(10086,"用户未登录");
        }
        String token = authHeader.substring(7);

        //验证token
        if(StringUtils.isEmpty(token)){
            throw new CgErrorException(10086,"用户未登录");
        }

        JwtUtil.checkToken(token);

        return true;
    }

}
