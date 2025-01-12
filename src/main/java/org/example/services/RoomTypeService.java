package org.example.services;

import org.example.models.RoomType;
import org.example.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {
    public RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomTypeById(Integer roomTypeId) {
        return roomTypeRepository.findById(roomTypeId).orElse(null);
    }

    public RoomType addRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

}
