package com.sparta.gaeppa.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@Primary
public class AuthenticationConfig {
    @Bean
    public AuthenticationConfiguration authenticationConfig() {
        return new AuthenticationConfiguration();
    }
}
