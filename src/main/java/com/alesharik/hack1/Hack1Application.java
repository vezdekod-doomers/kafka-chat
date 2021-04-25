package com.alesharik.hack1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hack1Application {

    public static void main(String[] args) {
        var bean = SpringApplication.run(Hack1Application.class, args).getBean(Runner.class);
        bean.run();
    }

}
