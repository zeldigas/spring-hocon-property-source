package com.github.zeldigas.example.hocon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {


    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/config")
    AppConfig config(){
        return appConfig;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
