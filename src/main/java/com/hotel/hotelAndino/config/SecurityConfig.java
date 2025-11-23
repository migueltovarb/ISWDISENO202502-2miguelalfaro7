package com.hotel.hotelAndino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // quitar CSRF
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // permitir todo
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // evitar bloqueos
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }
}
