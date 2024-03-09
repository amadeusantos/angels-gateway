package com.angels.eurekaserver.config;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.TestingAuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    @Profile("!dev")
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
        EurekaInstanceConfigBean b = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        b.setDataCenterInfo(info);
        return b;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }

//    @Bean
//    public AuthenticationBa authenticationProvider(HttpSecurity) {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.set(new TestingAuthenticationProvider());
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
}
