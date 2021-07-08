package com.capstone.mobileeats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class MobileEatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileEatsApplication.class, args);
    }

}
