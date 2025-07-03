package com.student.main.security;

import com.student.main.multitenant.TenantInterceptor;
import com.student.main.security.utils.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//This class is for to resolve cors issue
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private TenantInterceptor requestInterceptor;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(frontendUrl)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL)
//                        .exposedHeaders("Access-Control-Allow-Origin",
//                                "Access-Control-Allow-Methods",
//                                "Access-Control-Allow-Headers",
//                                "Access-Control-Max-Age",
//                                "Access-Control-Request-Headers",
//                                "Access-Control-Request-Method", "Authorization")
                        .allowCredentials(true)
                        .maxAge(3600);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(requestInterceptor);
            }
        };
    }
}

