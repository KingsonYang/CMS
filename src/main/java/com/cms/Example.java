package com.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ImportResource;

@MapperScan("com.cms.dao")
@ImportResource(locations={"classpath:mykaptcha.xml"})
@SpringBootApplication
public class Example {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}