package com.nnk.springboot.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration to set authentication and authorization parameters on specific pages of the app.
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private  final CustomUserDetailsService customUserDetailsService;

    /**
     * Bean of the filter chain.
     *
     * @param http The parameter used to configure the web security.
     * @return The configuration of the filter chain.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/403", "/css/**", "/js/**", "/images/**")
                        .permitAll()
                        .requestMatchers("/secure/article-details", "/user/**")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/403")))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean of the authentication rules.
     *
     * @param http                  The parameter used to configure the web security.
     * @param bCryptPasswordEncoder The password encoder.
     * @return The authentication configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);

        return authenticationManagerBuilder.build();
    }

}
