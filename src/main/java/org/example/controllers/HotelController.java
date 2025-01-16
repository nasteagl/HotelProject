package org.example.controllers;


import org.example.dto.ClientDto;
import org.example.dto.HotelDto;
import org.example.models.Hotel;
import org.example.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // get all hotels
    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotels() {
        return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
    }

    // Retrieve one hotel
    @GetMapping("/{hotel_id}")
    public ResponseEntity<List<HotelDto>> getHotel(@PathVariable Integer hotel_id) {
        HotelDto hotel = hotelService.getHotel(hotel_id);
        if (hotel != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        hotelService.addHotel(hotel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update a hotel by ID
    @PutMapping
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        if (hotel != null) {
            hotelService.updateHotel(hotel);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a hotel by ID
    @DeleteMapping("/{hotel_id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Integer hotel_id) {
        HotelDto hotel = hotelService.getHotel(hotel_id);
        if (hotel != null) {
            hotelService.deleteHotel(hotel_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
