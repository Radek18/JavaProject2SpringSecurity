package com.example.JavaProject2SpringSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .oauth2Login(oauth2 -> oauth2
                 .authorizationEndpoint(authorization -> authorization
                     .authorizationRequestResolver(
                         new CustomAuthorizationRequestResolver(
                             this.clientRegistrationRepository
                         )
                     )
                 )
            )
        ;

        http.oauth2Client();

        return http.build();

    }

}