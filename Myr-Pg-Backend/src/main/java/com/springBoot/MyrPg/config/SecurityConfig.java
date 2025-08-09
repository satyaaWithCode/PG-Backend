package com.springBoot.MyrPg.config;

import com.springBoot.MyrPg.security.JwtEntryPoint;
import com.springBoot.MyrPg.security.JwtFilter;
import com.springBoot.MyrPg.security.JwtHelper;
import com.springBoot.MyrPg.service.impl.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity//for rol based auth

public class SecurityConfig {
    private final JwtHelper helper;
    private final JwtFilter filter;
    private final JwtEntryPoint jwtEntryPoint;
    private final CustomUserDetailService userDetailService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors->{})
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//once  if we login first tyme  then server remember us  //You don’t have to send your token or credentials each time.
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
//                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                                .requestMatchers("/auth/register", "/auth/login").permitAll()
                                .requestMatchers("/api/pg/**").permitAll()
                                .requestMatchers("/ws/**", "/topic/**", "/api/ws/send").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()//for download file //when ui triggered Download button it s mandatory http option
                        // Any other endpoint requires authentication
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);//filter check here inside the header token and username have or not

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//does not store plane text pw
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {//when will login it will verify right
        return config.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); //will fetch username & pw
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
