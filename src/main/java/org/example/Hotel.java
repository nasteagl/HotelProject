package org.example;
import java.util.*;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Hotel {
    private int hotel_id;
    private String hotelAddress;
    private String hotelCity;
    private String hotelCountry;
    private String hotelPhone;
    private String hotelEmail;
    List<Client> clients;
    List<RoomsType> rooms;
    

}
