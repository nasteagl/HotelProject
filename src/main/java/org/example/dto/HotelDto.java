package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.example.models.Hotel;

@Data
@AllArgsConstructor
@Value
@Builder
public class HotelDto {
    Integer hotel_id;
    String hotelAddress;
    String hotelCity;
    String hotelCountry;
    String hotelPhone;
    String hotelEmail;

    // Entity -> Dto
    static public HotelDto fromHotel(Hotel hotel) {
        return HotelDto.builder()
                .hotel_id(hotel.getHotel_id())
                .hotelAddress(hotel.getHotelAddress())
                .hotelCity(hotel.getHotelCity())
                .hotelCountry(hotel.getHotelCountry())
                .hotelPhone(hotel.getHotelPhone())
                .hotelEmail(hotel.getHotelEmail())
                .build();
    }

    // Dto -> Entity
    static public Hotel fromHotelDto(HotelDto hotelDto) {
        return Hotel.builder()
                .hotel_id(hotelDto.getHotel_id())
                .hotelAddress(hotelDto.getHotelAddress())
                .hotelCity(hotelDto.getHotelCity())
                .hotelCountry(hotelDto.getHotelCountry())
                .hotelPhone(hotelDto.getHotelPhone())
                .hotelEmail(hotelDto.getHotelEmail())
                .build();
    }
}
