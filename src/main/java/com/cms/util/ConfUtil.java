package com.cms.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qiaolulu
 * 获取springbooot中上下文的自定义配置信息
 */
@Component
@ConfigurationProperties(prefix = "user")
public class ConfUtil {

    private String default_pwd;

    public String getDefault_pwd() {
        return default_pwd;
    }

    public void setDefault_pwd(String default_pwd) {
        this.default_pwd = default_pwd;
    }

}
