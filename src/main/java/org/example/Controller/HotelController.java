package org.example.Controller;


import org.example.model.Hotel;
import org.example.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // Create a new hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.hotelRepository.save(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    // Retrieve all hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.hotelRepository.findAll();
        return ResponseEntity.ok(hotels);
    }

    // Retrieve a hotel by ID
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Integer id) {
        Optional<Hotel> hotel = hotelService.hotelRepository.findById(id);
        return hotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a hotel by ID
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Integer id, @RequestBody Hotel updatedHotel) {
        Optional<Hotel> existingHotel = hotelService.hotelRepository.findById(id);
        if (existingHotel.isPresent()) {
            updatedHotel.setHotel_id(id); // Ensure the ID remains consistent
            Hotel savedHotel = hotelService.hotelRepository.save(updatedHotel);
            return ResponseEntity.ok(savedHotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a hotel by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Integer id) {
        if (hotelService.hotelRepository.existsById(id)) {
            hotelService.hotelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
