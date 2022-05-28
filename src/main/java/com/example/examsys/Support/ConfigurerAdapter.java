package com.example.examsys.Support;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//解决跨域问题
@Configuration
public class ConfigurerAdapter implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(false)
                .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS")
                .allowedOrigins("*")
        ;
    }
}