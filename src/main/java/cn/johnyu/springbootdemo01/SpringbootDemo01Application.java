package cn.johnyu.springbootdemo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo01Application.class, args);
    }

}

