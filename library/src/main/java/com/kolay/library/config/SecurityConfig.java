package com.kolay.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain basicFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authRequests -> authRequests
                        .requestMatchers("/api/users").hasAuthority("PRIV_USER_MANAGEMENT")
                        .requestMatchers(HttpMethod.POST, "/api/books").hasAuthority("PRIV_BOOK_MANAGEMENT")
                        .requestMatchers(HttpMethod.POST, "/api/books/**").hasAuthority("PRIV_BOOK_MANAGEMENT")
                        .requestMatchers("/api/books/all").hasAuthority("PRIV_BOOK_MANAGEMENT")
                        .requestMatchers("/api/books").hasAuthority("PRIV_BOOK_ACCESS")
                        .requestMatchers("/api/books/**").hasAuthority("PRIV_BOOK_ACCESS")
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())
                .csrf().disable()
                .build();
    }

}
