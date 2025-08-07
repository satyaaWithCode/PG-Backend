package com.springBoot.MyrPg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // or "http://localhost:3000" during dev
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);//ui to backend send request
    }
}
