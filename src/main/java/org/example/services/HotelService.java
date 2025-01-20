package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.ClientDto;
import org.example.dto.HotelDto;
import org.example.models.Client;
import org.example.models.Hotel;
import org.example.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<HotelDto> getHotels() {
        return hotelRepository.findAllHotels()
                .stream()
                .map(HotelDto::fromHotel)
                .toList();
    }

    public HotelDto getHotel(Integer hotel_Id) {
        Hotel hotel = hotelRepository.findByIdHotel(hotel_Id);
        if (null == hotel) {
            return null;
        } else {
            return HotelDto.fromHotel(hotel);
        }
    }

    public void addHotel(HotelDto hotel) {
        hotelRepository.saveHotel(HotelDto.fromHotelDto(hotel));
    }

    public void updateHotel(HotelDto hotel) {
        hotelRepository.updateHotel(HotelDto.fromHotelDto(hotel));
    }

    public void deleteHotel(Integer hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    public void patchHotel(Integer hotelId, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findByIdHotel(hotelId);
        if (hotelDto.getHotelAddress() != null) {
            hotel.setHotelAddress(hotelDto.getHotelAddress());
        }
        if (hotelDto.getHotelCity() != null) {
            hotel.setHotelCity(hotelDto.getHotelCity());
        }
        if (hotelDto.getHotelCountry() != null) {
            hotel.setHotelCountry(hotelDto.getHotelCountry());
        }
        if (hotelDto.getHotelPhone() != null) {
            hotel.setHotelPhone(hotelDto.getHotelPhone());
        }
        if (hotelDto.getHotelEmail() != null) {
            hotel.setHotelEmail(hotelDto.getHotelEmail());
        }

        hotelRepository.saveHotel(hotel);
    }
}




