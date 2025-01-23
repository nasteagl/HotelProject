package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import org.example.models.Hotel;

import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
@JsonIgnoreProperties({ "rooms", "clients" })
public class HotelDto {
    Integer hotel_id;
    String hotelAddress;
    String hotelCity;
    String hotelCountry;
    String hotelPhone;
    String hotelEmail;
    List<RoomDto> rooms;
    List<ClientDto> clients;

    // Entity -> Dto
    static public HotelDto fromHotel(Hotel hotel) {
        return HotelDto.builder()
                .hotel_id(hotel.getHotel_id())
                .hotelAddress(hotel.getHotelAddress())
                .hotelCity(hotel.getHotelCity())
                .hotelCountry(hotel.getHotelCountry())
                .hotelPhone(hotel.getHotelPhone())
                .hotelEmail(hotel.getHotelEmail())
//                .rooms(hotel.getRooms().stream().map(RoomDto::fromRoom).collect(Collectors.toList()))
//                .clients(hotel.getClients().stream().map(ClientDto::fromClient).collect(Collectors.toList()))
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
