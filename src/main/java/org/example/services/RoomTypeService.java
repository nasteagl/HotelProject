package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoomTypeDto;
import org.example.models.RoomType;
import org.example.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;



    public List<RoomTypeDto> getAllRoomTypes() {
        return roomTypeRepository.findAll().stream().map(RoomTypeDto::fromRoomType).toList();
    }

    public RoomTypeDto getRoomTypeById(Integer roomTypeId) {
      RoomType roomType = roomTypeRepository.findById(roomTypeId).get();
        return RoomTypeDto.fromRoomType(roomType);
    }

    public void addRoomType(RoomTypeDto roomType) {
        roomTypeRepository.save(RoomTypeDto.fromRoomTypeDto(roomType));
    }
}