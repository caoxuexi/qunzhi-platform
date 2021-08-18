package com.lq.mybatisgeneratordemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.lq.mybatisgeneratordemo.mbg.mapper")
public class MyBatisConfig {

}
