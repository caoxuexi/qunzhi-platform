package com.xidian.qunzhi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.xidian.qunzhi.mapper")
public class QunzhiApplication {
    private static final Logger LOG= LoggerFactory.getLogger(QunzhiApplication.class);
    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(QunzhiApplication.class);
        Environment env =app.run(args).getEnvironment();
        LOG.info("启动成功！");
        LOG.info("地址：\t http://127.0.0.1:{}",env.getProperty("server.port"));

    }

}
