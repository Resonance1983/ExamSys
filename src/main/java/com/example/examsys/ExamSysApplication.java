package com.example.examsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ExamSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamSysApplication.class, args);
    }

}
