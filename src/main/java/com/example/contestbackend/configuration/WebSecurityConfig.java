package com.example.contestbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig {
    @Lazy
    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().cors().and()
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(403);
                    response.getWriter().println(authException.getMessage());
                    response.flushBuffer();
                }).and()
                .formLogin().successHandler((request, response, authentication) -> {
                    response.getWriter().print(authentication.getAuthorities().stream().findAny().get());
                    response.flushBuffer();
                }).failureHandler((request, response, exception) -> {
                    response.setStatus(401);
                }).and()
                .logout().logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(200);
                }).and()
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/form/**").authenticated()
//                .antMatchers("/tasks").hasAnyRole("ADMIN","COORDINATOR")
                .anyRequest().permitAll().and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT")
                        .allowedOrigins("http://localhost:5173/");
            }
        };
    }
}