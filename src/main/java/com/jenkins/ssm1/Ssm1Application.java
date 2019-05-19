package com.jenkins.ssm1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class Ssm1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ssm1Application.class, args);
    }

}
