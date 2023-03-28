package org.example.oj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("org.example.oj.dao")
@EnableTransactionManagement
@ServletComponentScan
public class OjApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjApplication.class, args);
    }
}
