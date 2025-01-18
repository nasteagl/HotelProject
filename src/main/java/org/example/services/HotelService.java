package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.models.Hotel;
import org.example.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotel(Integer hotel_id) {
        return hotelRepository.findById(hotel_id).orElse(null);
    }

    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(Integer hotel_id) {
        hotelRepository.deleteById(hotel_id);
    }
}

