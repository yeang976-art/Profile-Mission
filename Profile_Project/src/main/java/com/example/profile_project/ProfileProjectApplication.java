package com.example.profile_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProfileProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileProjectApplication.class, args);
    }

}
