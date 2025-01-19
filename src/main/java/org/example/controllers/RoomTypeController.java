package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoomTypeDto;
import org.example.services.RoomTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/room_type")
@RequiredArgsConstructor
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    public List<RoomTypeDto> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @GetMapping("/{roomTypeId}")
    public RoomTypeDto getRoomType(@PathVariable Integer roomTypeId) {
        return roomTypeService.getRoomTypeById(roomTypeId);
    }

    @PostMapping
    public ResponseEntity<Object> addRoomType(@RequestBody RoomTypeDto roomTypeDto) {
        if(roomTypeDto != null) {
            roomTypeService.addRoomType(roomTypeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{roomType_id}")
    public ResponseEntity<RoomTypeDto> updateRoomType(@PathVariable Integer roomType_id, @RequestBody RoomTypeDto roomTypeBody) {
        RoomTypeDto roomTypeDto = roomTypeService.getRoomTypeById(roomType_id);
        if (roomTypeDto != null) {
              roomTypeService.patchRoomType(roomType_id, roomTypeBody);
              return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}