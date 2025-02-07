package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoomDto;
import org.example.models.Room;
import org.example.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    @Transactional(readOnly = true)
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream().map(RoomDto::fromRoom).toList();
    }
    @Transactional(readOnly = true)
    public RoomDto getRoomById(Integer roomId) {
        Room room = roomRepository.findById(roomId);
        if (room == null) {
            return null;
        }else {
            return RoomDto.fromRoom(room);
        }}


    @Transactional
    public RoomDto addRoom(RoomDto roomDto) {
        Room room= roomRepository.save(RoomDto.fromRoomDto(roomDto));
        return RoomDto.fromRoom(room);
    }


    @Transactional
    public RoomDto updateRoom(RoomDto roomDto) {
        Room room = roomRepository.update(RoomDto.fromRoomDto(roomDto));
        return RoomDto.fromRoom(room);
    }


    @Transactional
    public void deleteRoom(Integer roomId) {
        roomRepository.deleteById(roomId);
    }


    @Transactional
    public RoomDto patchRoom(Integer roomId, RoomDto roomDto) {
        Room room = roomRepository.findById(roomId);
        if(roomDto != null && roomDto.getId_room() >0) {
            room.setId_room(roomDto.getId_room());
        }
        if(roomDto != null && roomDto.getFloor() >0) {
            room.setFloor(roomDto.getFloor());
        }
        if(roomDto != null && roomDto.getNumber() >0) {
            room.setNumber(roomDto.getNumber());
        }
        if(roomDto != null && roomDto.getPrice() > 0) {
            room.setPrice(roomDto.getPrice());
        }
        if(roomDto != null && roomDto.getBeds() >0) {
            room.setBeds(roomDto.getBeds());
        }
        if(roomDto != null) {
            room.setReserved(roomDto.getReserved());
        }
        roomRepository.save(room);
        return RoomDto.fromRoom(room);
    }
}


