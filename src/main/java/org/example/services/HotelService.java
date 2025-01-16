package org.example.services;

import org.example.dto.ClientDto;
import org.example.dto.HotelDto;
import org.example.models.Client;
import org.example.models.Hotel;
import org.example.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    public HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    public List<HotelDto> getHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(el -> modelMapper.map(el, HotelDto.class))
                .collect(Collectors.toList());
    }

    public HotelDto getHotel(Integer hotel_id) {
        return modelMapper.map(hotelRepository.findById(hotel_id), HotelDto.class);
    }

    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);

    }

    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(Integer hotel_id) {
        modelMapper.map(hotelRepository.findById(hotel_id), hotelRepository);
        hotelRepository.deleteById(hotel_id);
    }

    //    Cu dto si modelaMapper
//
//    public void addHotel(HotelDto hotel) {
//        modelMapper.map(hotel, hotelRepository);
//        hotelRepository.save(modelMapper.map(hotel, Hotel.class));
//    }
//
//    public void updateHotel(HotelDto hotel) {
//        modelMapper.map(hotel, hotelRepository);
//        hotelRepository.save(modelMapper.map(hotel, Hotel.class));
//    }
//
//    public void deleteHotel(Integer hotel_id) {
//        modelMapper.map(hotelRepository.findById(hotel_id), hotelRepository);
//        hotelRepository.deleteById(hotel_id);
//    }

//    Fără dto

//    public List<Hotel> getHotels() {
//        return hotelRepository.findAll();
//    }

//    public Hotel getHotel(Integer hotel_id) {
//        return hotelRepository.findById(hotel_id).orElse(null);
//    }
//
//    public void addHotel(Hotel hotel) {
//        hotelRepository.save(hotel);
//    }
//
//    public void updateHotel(Hotel hotel) {
//        hotelRepository.save(hotel);
//    }
//
//    public void deleteHotel(Integer hotel_id) {
//        hotelRepository.deleteById(hotel_id);
//    }

//    Cu dto fara modelMapper

//    public List<HotelDto> getHotel() {
//        return hotelRepository.findAll()
//                .stream()
//                .map(hotel -> new HotelDto(
//                        hotel.hotel_id(),
//                        hotel.hotelAddress(),
//                        hotel.hotelCity(),
//                        hotel.hotelCountry(),
//                        hotel.hotelPhone(),
//                        hotel.hotelEmail()
//                )).collect(Collectors.toList());
//    }
}

