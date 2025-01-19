package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoomDto;
import org.example.models.Room;
import org.example.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public List<RoomDto> getAllRooms() {

        return roomRepository.findAll().stream().map(RoomDto::fromRoom).toList();
    }

    public RoomDto getRoomById(Integer roomId) {
        Room room = roomRepository.findById(roomId);
        if (room == null) {
            return null;
        }else {
            return RoomDto.fromRoom(room);
        }}

    public void addRoom(RoomDto room) {
        roomRepository.save(RoomDto.fromRoomDto(room));
    }
    public void updateRoom(RoomDto room) {
        roomRepository.save(RoomDto.fromRoomDto(room));
    }
    public void deleteRoom(Integer roomId) {roomRepository.deleteById(roomId);
    }

    public void patchRoom(Integer roomId, RoomDto roomDto) {
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
           room.setReserved(roomDto.isReserved());
       }
       roomRepository.save(room);
    }
}


