package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.HotelDto;
import org.example.models.Hotel;
import org.example.repositories.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Transactional(readOnly = true)
    public List<HotelDto> getHotels() {
        return hotelRepository.findAllHotels()
                .stream()
                .map(HotelDto::fromHotel)
                .toList();
    }

    @Transactional(readOnly = true)
    public HotelDto getHotel(Integer hotel_Id) {
        Hotel hotel = hotelRepository.findByIdHotel(hotel_Id);
        if (null == hotel) {
            return null;
        } else {
            return HotelDto.fromHotel(hotel);
        }
    }

    @Transactional
    public HotelDto addHotel(HotelDto hotelDto) {
        Hotel hotel = hotelRepository.saveHotel(HotelDto.fromHotelDto(hotelDto));
        return HotelDto.fromHotel(hotel);
    }

    @Transactional
    public HotelDto updateHotel(HotelDto hotelDto) {
        Hotel hotel = hotelRepository.updateHotel(HotelDto.fromHotelDto(hotelDto));
        return HotelDto.fromHotel(hotel);
    }

    @Transactional
    public HotelDto deleteHotel(Integer hotelId) {
        Hotel hotel = hotelRepository.deleteById(hotelId);
        return HotelDto.fromHotel(hotel);
    }

    @Transactional
    public HotelDto patchHotel(Integer hotelId, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findByIdHotel(hotelId);
        if (null == hotel) {
            throw new RuntimeException("Hotel not found");
        } else {
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
            return HotelDto.fromHotel(hotel);
        }
    }
}