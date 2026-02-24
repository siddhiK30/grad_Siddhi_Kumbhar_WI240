package com.example.demo.util;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component

public class ProfileChecker {

    @Autowired
    private Environment env;

    @PostConstruct
    public void printProfile() {
        System.out.println("=================================");
        System.out.println("ACTIVE PROFILE: " + Arrays.toString(env.getActiveProfiles()));
        System.out.println("=================================");
    }
}
