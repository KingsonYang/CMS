package com.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@MapperScan("com.cms.dao")
@SpringBootApplication
public class Example {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}