package com.coursecube.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class StartMyBootApp /*extends SpringBootServletInitializer*/ {
    public static void main(String[] args) {
        SpringApplication.run(StartMyBootApp.class, args);
    }
}
