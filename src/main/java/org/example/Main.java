package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class Main {
    Hotel hotel = new Hotel.HotelBuilder().hotel_id(1).hotelAddress("Stefan cel Mare").hotelCity("Chisinau").hotelCountry("Md").build();
    Client client = new Client.ClientBuilder().setClientId(1).setFirstname("Ion").setLastname("Ionescu").setAge(24).build();
    Room room = new Room.RoomBuilder().setIdRoom(1).setBeds(3).setFloor(2).setNumber(24).build();
    RoomsType roomsType = new RoomsType.RoomTypeBuilder().build();
    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
//        System.out.println("Hello World");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HotelConfig.class);

        Hotel hotel = context.getBean(Hotel.class);

        System.out.println(hotel);
    }
}