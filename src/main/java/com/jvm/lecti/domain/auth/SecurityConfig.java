package com.jvm.lecti.domain.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jvm.lecti.domain.service.CustomUserDetailsService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   private final CustomUserDetailsService userDetailsService;

   private final JwtAuthorizationFilter jwtAuthorizationFilter;

   public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
      this.userDetailsService = customUserDetailsService;
      this.jwtAuthorizationFilter = jwtAuthorizationFilter;

   }

   @Bean
   public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
      AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
      authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
      return authenticationManagerBuilder.build();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
          .authorizeHttpRequests(authorize -> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR)
                .permitAll()
                .requestMatchers("/api/auth/**")
                .permitAll()// Allow unauthenticated access to /api/auth/**
                .requestMatchers("/api/exercise/obtainTest")
                .permitAll()// Allow unauthenticated access to /api/auth/**
                .anyRequest()
                .authenticated() // All other requests require authentication
          )
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management
          )
          .addFilterBefore(jwtAuthorizationFilter,
                UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter

      return http.build();
   }

   @SuppressWarnings("deprecation")
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new MessageDigestPasswordEncoder("SHA-256");
   }

}
