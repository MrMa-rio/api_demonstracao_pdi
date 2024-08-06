package com.criar.pdi.demonstracao.config.security;


import com.criar.pdi.demonstracao.components.Filters.FilterSecurity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private FilterSecurity filterSecurity;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    public SecurityConfiguration(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/swagger-ui/**").permitAll();
                    req.requestMatchers("/v3/api-docs/**").permitAll();
                    req.requestMatchers(HttpMethod.POST,"/auth/register").permitAll();
                    req.requestMatchers("/**").hasAnyRole("ADMINISTRADOR", "PROPRIETARIO");
                    req.requestMatchers(HttpMethod.GET,
                            "/user/**",
                            "/store/**",
                            "/review/**",
                            "/product/**",
                            "/shopping-cart/**",
                            "/product-in-cart/**").hasRole("CLIENTE");
                    req.requestMatchers(HttpMethod.POST, "/user/**", "/review/**", "/order/**").hasRole("CLIENTE");
                    req.anyRequest().authenticated();
                }).addFilterBefore(filterSecurity, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exception) -> {
                    exception.authenticationEntryPoint(customAuthenticationEntryPoint);
                })
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}