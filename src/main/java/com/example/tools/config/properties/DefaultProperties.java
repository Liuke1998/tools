package com.example.tools.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的配置
 */
@Configuration
public class DefaultProperties {

    @Bean
    @ConfigurationProperties(prefix = "http")
    public HttpClientProperties httpClientProperties() {
        return new HttpClientProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.mail")
    public MailProperties MailProperties(){
        return new MailProperties();
    }

}
