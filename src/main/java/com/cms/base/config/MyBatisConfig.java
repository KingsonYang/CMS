package com.cms.base.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.cms.dao")
public class MyBatisConfig {

}
