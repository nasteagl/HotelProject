package org.example.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Table(name = "hotel", schema = "hotel_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "hotel_address", nullable = false, length = 100)
    private String hotelAddress;

    @Column(name = "hotel_city", nullable = false, length = 100)
    private String hotelCity;

    @Column(name = "hotel_country", nullable = false, length = 100)
    private String hotelCountry;

    @Column(name = "hotel_phone", nullable = false, unique = true, length = 50)
    private String hotelPhone;

    @Column(name = "hotel_email", nullable = false, length = 100)
    private String hotelEmail;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hotel")
    private Client client;

    public Hotel(HotelBuilder builder) {
        this.hotel_id = builder.hotel_id;
        this.hotelAddress = builder.hotelAddress;
        this.hotelCity = builder.hotelCity;
        this.hotelCountry = builder.hotelCountry;
        this.hotelPhone = builder.hotelPhone;
        this.hotelEmail = builder.hotelEmail;
    }


    public static class HotelBuilder{
        private int hotel_id;
        private String hotelAddress;
        private String hotelCity;
        private String hotelCountry;
        private String hotelPhone;
        private String hotelEmail;


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
        public Hotel build(){
            return new Hotel(this);
        }
    }


}
