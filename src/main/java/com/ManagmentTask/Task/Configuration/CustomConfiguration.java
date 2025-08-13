package com.ManagmentTask.Task.Configuration;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableAutoConfiguration
public class CustomConfiguration {
    @Bean
    public SecurityFilterChain  customFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                   csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                         auth->auth
                                 .requestMatchers("/public/**").permitAll()
                                 .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
         return httpSecurity.build();

    }

     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
          return authenticationConfiguration.getAuthenticationManager();
     }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new  BCryptPasswordEncoder();
    }
}
