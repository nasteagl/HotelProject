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

    public Hotel(HotelBuilder builder) {
        this.hotel_id = builder.hotel_id;
        this.hotelAddress = builder.hotelAddress;
        this.hotelCity = builder.hotelCity;
        this.hotelCountry = builder.hotelCountry;
        this.hotelPhone = builder.hotelPhone;
        this.hotelEmail = builder.hotelEmail;
        this.clients = builder.clients;
        this.rooms = builder.rooms;

    }


    public static class HotelBuilder{
        private int hotel_id;
        private String hotelAddress;
        private String hotelCity;
        private String hotelCountry;
        private String hotelPhone;
        private String hotelEmail;
        private List<Client> clients;
        private List<RoomsType> rooms;

        public HotelBuilder hotel_id(int hotel_id) {
            this.hotel_id = hotel_id;
            return this;
        }
        public HotelBuilder hotelAddress(String hotelAddress) {
            this.hotelAddress = hotelAddress;
            return this;
        }
        public HotelBuilder hotelCity(String hotelCity) {
            this.hotelCity = hotelCity;
            return this;
        }
        public HotelBuilder hotelCountry(String hotelCountry) {
            this.hotelCountry = hotelCountry;
            return this;
        }
        public HotelBuilder hotelPhone(String hotelPhone) {
            this.hotelPhone = hotelPhone;
            return this;
        }
        public HotelBuilder hotelEmail(String hotelEmail) {
            this.hotelEmail = hotelEmail;
            return this;
        }
        public HotelBuilder clients(List<Client> clients) {
            this.clients = clients;
            return this;
        }
        public HotelBuilder rooms(List<RoomsType> rooms) {
            this.rooms = rooms;
            return this;
        }
        public Hotel build(){
            return new Hotel(this);
        }
    }



}
