package com.borgymanotoy.samples.jwt.springbootjwt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by borgymanotoy on 6/20/17.
 * Source: https://auth0.com/blog/securing-spring-boot-with-jwts/
 * Github: https://github.com/auth0-blog/spring-boot-jwts
 */


@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class DemoApplication {

    @RequestMapping("/")
    String hello() {
        return "Hello Davao City!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
