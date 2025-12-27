package com.example.demo.config;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer cors(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry r){
                r.addMapping("/**").allowedMethods("*");
            }
        };
    }
}
