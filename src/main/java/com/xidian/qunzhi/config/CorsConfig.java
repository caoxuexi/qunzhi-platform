package com.xidian.qunzhi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    public CorsConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
        //CorsFilter导.web.filter.CorsFilter下的
        //1.添加cors配置信息
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedOrigin("*");
        // 设置是否发送cookie信息
        config.setAllowCredentials(true);
        // 设置允许请求的方式  全部
        config.addAllowedMethod("*");
        // 设置允许的header
        config.addAllowedHeader("*");

        //2. 为url添加映射路径 选第一个
        UrlBasedCorsConfigurationSource corsSource=new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);

        //3. 返回中心定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
