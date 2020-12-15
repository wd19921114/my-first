package cn.ppz111.my.config;

import cn.ppz111.my.handler.Hall;
import cn.ppz111.my.handler.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2019-07-10
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns().excludePathPatterns("/admin/login").excludePathPatterns("/page/viewPageList");
    }

}

