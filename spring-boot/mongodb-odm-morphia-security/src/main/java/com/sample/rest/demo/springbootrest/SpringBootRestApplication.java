package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.CustomUserDetails;
import com.sample.rest.demo.springbootrest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
@ComponentScan(basePackages = "com.sample.rest.demo.springbootrest")
@PropertySources({
    @PropertySource("classpath:application.properties")
})
public class SpringBootRestApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources(SpringBootRestApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApplication.class, args);
    }

}