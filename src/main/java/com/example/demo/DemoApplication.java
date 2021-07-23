package com.example.demo;

import com.example.demo.jwt.JwtUitl;
import com.example.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        User u=new User();
        u.setId(1);
        u.setPassword("111");
        System.out.println("JWT"+JwtUitl.createToken(u));
    }

}
