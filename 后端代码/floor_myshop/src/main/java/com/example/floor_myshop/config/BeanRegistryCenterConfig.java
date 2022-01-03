package com.example.floor_myshop.config;

import com.example.floor_myshop.util.MailSendUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRegistryCenterConfig {
    @Bean
    public MailSendUtils mailSendUtils() {
        return new MailSendUtils();
    }
}
