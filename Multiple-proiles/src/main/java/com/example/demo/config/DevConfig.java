package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// @Configuration
// @Profile("dev")
// public class DevConfig {

//     @Bean
//     public String myenvironment() {
//            System.out.println("🔥 DEV PROFILE IS ACTIVE");
//         return "Development Environment";
//     }
// }


@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public String myenvironment() {
           System.out.println("🔥 DEV PROFILE IS ACTIVE");
        return "Development Environment";
    }
}
