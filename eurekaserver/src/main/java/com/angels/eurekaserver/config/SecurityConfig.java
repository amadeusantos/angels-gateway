//package com.angels.eurekaserver.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    public SecurityConfig(BasicAuthenticationFilter basicAuthenticationFilter) {
//        this.basicAuthenticationFilter = basicAuthenticationFilter;
//    }
//
//    private final BasicAuthenticationFilter basicAuthenticationFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                authorizationManagerRequestMatcherRegistry.requestMatchers("").permitAll().anyRequest().authenticated())
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilter(basicAuthenticationFilter).cors(
//                        cors -> {
//                            try {
//                                cors.init(http);
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//
//        return http.build();
//    }
//
//}