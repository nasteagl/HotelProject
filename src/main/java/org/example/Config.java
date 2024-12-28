package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Hotel.class)
public class Config {

    @Bean
    public Client client() {
        return new Client.ClientBuilder().setClientId(1).setFirstname("Ion").setLastname("Popescu").build();
    }
}
