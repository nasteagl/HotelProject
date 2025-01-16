package org.example.services;

import org.example.models.RoomType;
import org.example.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomTypeById(Integer roomTypeId) {
        return roomTypeRepository.findById(roomTypeId).orElse(null);
    }

    public void addRoomType(RoomType roomType) {
        roomTypeRepository.save(roomType);
    }
}