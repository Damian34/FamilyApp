package com.familyApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.familyApp.repository")
public class FamilyApp {

    public static void main(String[] args) {
        SpringApplication.run(FamilyApp.class, args);
    }
}
