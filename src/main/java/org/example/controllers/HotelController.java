package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.Hotel;
import org.example.dto.HotelDto;
import org.example.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    // get all hotels
    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotels() {
        return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
    }

    // Retrieve one hotel
    @GetMapping("/{hotel_id}")
    public ResponseEntity<HotelDto> getHotel(@PathVariable Integer hotel_id) {
        HotelDto hotel = hotelService.getHotel(hotel_id);
        if (hotel != null) {
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotel) {
        return new ResponseEntity<>(hotelService.addHotel(hotel), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto hotel) {
        return new ResponseEntity<>(hotelService.updateHotel(hotel), HttpStatus.OK);
    }

    // Delete a hotel by ID
    @DeleteMapping("/{hotel_id}")
    public ResponseEntity<HotelDto> deleteHotel(@PathVariable Integer hotel_id) {
        HotelDto hotel = hotelService.getHotel(hotel_id);
        if (hotel != null) {
            hotelService.deleteHotel(hotel_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{hotel_id}")
    public ResponseEntity<HotelDto> patchHotel (@PathVariable Integer hotel_id, @RequestBody HotelDto hotelbody) {
        HotelDto hotel = hotelService.getHotel(hotel_id);
        if (hotel != null) {
            hotelService.patchHotel(hotel_id, hotelbody);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
