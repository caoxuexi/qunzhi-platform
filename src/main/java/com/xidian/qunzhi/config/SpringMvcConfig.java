package com.xidian.qunzhi.config;

import com.xidian.qunzhi.core.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/redis/**", //redisTemplate的本质也是向redis发http请求，所以这里redis接口要放开
                        //用户登录注册等路由
                        "/user/login",
                        "/user/register",
                        "/adminUser/login",  //管理员登录路由
                        "/captcha/generate", //二维码生成路由
                        // swagger2需要放行的资源
                        "/doc.html",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/webjars/**"
                );
    }
}
