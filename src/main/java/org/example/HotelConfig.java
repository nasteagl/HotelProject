package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class HotelConfig {

    @Bean
    public Client client(){
        return new Client.ClientBuilder().build();
    }

    @Bean
    public RoomsType roomType() {
        return new RoomsType.RoomTypeBuilder().build();
    }

    @Bean
    public Hotel hotel() {
        return new Hotel.HotelBuilder().hotelCity("Chisinau").build();
    }




}
