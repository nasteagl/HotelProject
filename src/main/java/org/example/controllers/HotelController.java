package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.Hotel;
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
    public ResponseEntity<List<Hotel>> getHotels() {
        return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
    }

    // Retrieve one hotel
    @GetMapping("/{hotel_id}")
    public ResponseEntity<List<Hotel>> getHotel(@PathVariable Integer hotel_id) {
        Hotel hotel = hotelService.getHotel(hotel_id);
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
        Hotel hotel = hotelService.getHotel(hotel_id);
        if (hotel != null) {
            hotelService.deleteHotel(hotel_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
