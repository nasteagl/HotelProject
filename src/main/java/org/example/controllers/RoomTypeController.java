package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.RoomType;
import org.example.services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("room_type")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @GetMapping
    public List<RoomType> getRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @GetMapping("/{roomTypeId}")
    public RoomType getRoomType(@PathVariable Integer roomTypeId) {
        return roomTypeService.getRoomTypeById(roomTypeId);
    }

    @PostMapping
    public void addRoomType(@RequestBody RoomType roomType) {
        roomTypeService.addRoomType(roomType);
    }
}