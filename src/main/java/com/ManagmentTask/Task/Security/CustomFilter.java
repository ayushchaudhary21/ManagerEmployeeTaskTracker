package com.ManagmentTask.Task.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomFilter {
    private final JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf->csrf.disable())
                .sessionManagement(sessonConfi->sessonConfi
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers("/public/**","/auth/**").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        // adding our filter in the filter before the UserName password authentication filter


        return httpSecurity.build();

    }
}
