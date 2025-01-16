package org.example.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "hotel", schema = "hotel_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel", orphanRemoval = true)
    private List<Client> clients;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel", orphanRemoval = true)
    private List<Room> rooms;
}
