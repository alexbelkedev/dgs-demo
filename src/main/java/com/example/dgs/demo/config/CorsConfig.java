package com.example.dgs.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS configuration for local development.
 *
 * This configuration allows requests from a local frontend
 * (e.g., React running on http://localhost:3000) to reach the
 * GraphQL endpoint (/graphql). It is only active when the
 * application runs with the "dev" profile to avoid exposing
 * CORS settings in production.
 */
@Configuration
@Profile("dev")
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/graphql")
                .allowedOrigins("http://localhost:3000") // if a web app is running on another port, change this
                .allowedMethods("POST", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}