package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoomTypeDto;
import org.example.models.RoomType;
import org.example.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;


    @Transactional(readOnly = true)
    public List<RoomTypeDto> getAllRoomTypes() {
        return roomTypeRepository.findAll().stream().map(RoomTypeDto::fromRoomType).toList();
    }

    @Transactional(readOnly = true)
    public RoomTypeDto getRoomTypeById(Integer roomTypeId) {
      RoomType roomType = roomTypeRepository.findById(roomTypeId);
        return RoomTypeDto.fromRoomType(roomType);
    }

    @Transactional
    public RoomTypeDto addRoomType(RoomTypeDto roomType) {
        RoomType roomtype=roomTypeRepository.save(RoomTypeDto.fromRoomTypeDto(roomType));
        return  roomType;
    }

    @Transactional
    public RoomTypeDto patchRoomType(Integer roomTypeId, RoomTypeDto roomTypeDto) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId);
        if(roomTypeDto==null){
            throw new NullPointerException("roomTypeDto is null");
        }
        if(roomType==null){
            throw new NullPointerException("roomType is null");
        }
        roomTypeRepository.save(roomType);
        return RoomTypeDto.fromRoomType(roomType);
    }

    @Transactional
    public void deleteRoomType(Integer roomTypeId) {
           RoomType roomType = roomTypeRepository.findById(roomTypeId);
           roomTypeRepository.deleteByIdRoomType(roomTypeId);
    }

    @Transactional
    public RoomTypeDto updateRoomType(Integer roomTypeId, RoomTypeDto roomTypeDto) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId);
        if(roomTypeDto.getIdRoomsType() !=null){
            roomType.setIdRoomsType(roomTypeDto.getIdRoomsType());
        }
        if(roomTypeDto.getRoomTypeEnum() !=null){
            roomType.setRoomTypeEnum(roomTypeDto.getRoomTypeEnum());
        }
        roomTypeRepository.save(roomType);
        return RoomTypeDto.fromRoomType(roomType);
    }
}