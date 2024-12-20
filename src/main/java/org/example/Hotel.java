package org.example;
import java.util.*;
public class Hotel {
    private int hotel_id;
    private String hotelAddress;
    private String hotelCity;
    private String hotelCountry;
    private String hotelPhone;
    private String hotelEmail;
    List<Client> clients;
    List<RoomsType> rooms;
    
    public Hotel(int hotel_id, String hotelAddress, String hotelCity, String hotelCountry, String hotelPhone, String hotelEmail, List<Client> clients, List<RoomsType> rooms) {
        this.hotel_id = hotel_id;
        this.hotelAddress = hotelAddress;
        this.hotelCity = hotelCity;
        this.hotelCountry = hotelCountry;
        this.hotelPhone = hotelPhone;
        this.hotelEmail = hotelEmail;
        this.clients = clients;
        this.rooms = rooms;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }


    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }



    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }
}
