package com.startup.claimizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.waleedreda", "com.startup"})
@SpringBootApplication
public class ClaimizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaimizerApplication.class, args);
    }

}
