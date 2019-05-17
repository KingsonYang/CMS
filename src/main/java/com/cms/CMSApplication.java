package com.cms;


import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {QuartzAutoConfiguration.class})
@ImportResource(locations={"classpath:mykaptcha.xml"})
@EnableTransactionManagement/*允许事务注解执行*/
@EnableCaching
@EnableSwagger2
public class CMSApplication {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
        // 获取 Spring Boot 上下文
        ConfigurableApplicationContext ctx = SpringApplication.run(CMSApplication.class, args);

//        ConfUtil confUtil = (ConfUtil) ctx.getBean("confUtil");
//        confUtil.show();
//        ctx.close();

    }
}