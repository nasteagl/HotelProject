package org.example.controllers;

import org.example.models.RoomType;
import org.example.services.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("room_type")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

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