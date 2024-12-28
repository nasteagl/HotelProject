package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Hotel hotel = context.getBean("hotel", Hotel.class);
        System.out.println(hotel);

        System.out.println("Hello World");
    }
}