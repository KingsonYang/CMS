package com.cms;

import com.cms.util.ConfUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.cms.dao")
@ImportResource(locations={"classpath:mykaptcha.xml"})
@EnableTransactionManagement/*允许事务注解执行*/
@SpringBootApplication
public class Example {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
        // 获取 Spring Boot 上下文
        ConfigurableApplicationContext ctx = SpringApplication.run(Example.class, args);

//        ConfUtil confUtil = (ConfUtil) ctx.getBean("confUtil");
//        confUtil.show();
//        ctx.close();

    }
}