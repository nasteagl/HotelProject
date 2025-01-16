package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    private int hotel_id;
    private String hotelAddress;
    private String hotelCity;
    private String hotelCountry;
    private String hotelPhone;
    private String hotelEmail;
}
