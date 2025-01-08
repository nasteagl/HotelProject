package org.example.models;
import java.util.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Table(name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "hotelAddress", nullable = false, length = 100)
    private String hotelAddress;

    @Column(name = "hotelCity", nullable = false, length = 100)
    private String hotelCity;

    @Column(name = "hotelCountry", nullable = false, length = 100)
    private String hotelCountry;

    @Column(name = "hotelPhone", nullable = false, unique = true, length = 50)
    private String hotelPhone;

    @Column(name = "hotelEmail", nullable = false, length = 100)
    private String hotelEmail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hotel")
    List<Client> clients;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hotel")
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
            if(hotel_id < 0) {
                throw new IllegalArgumentException("Invalid hotel id");
            }
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
