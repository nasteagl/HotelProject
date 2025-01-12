package org.example.services;

import org.example.controllers.RoomController;
import org.example.models.Client;
import org.example.models.Room;
import org.example.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    public RoomRepository roomRepository;
//
//

//    @Autowired
//    public RoomService(RoomRepository roomRepository) {
//        this.roomRepository = roomRepository;
//    }
//
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Integer roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }
    public void deleteRoom(Integer roomId) {
        roomRepository.deleteById(roomId);
    }

//    public void patchRoom(Integer roomId, Integer new_roomType) {
//        roomRepository.findById(roomId).ifPresent(room -> {room.setRoomType(new_roomType);})
//    }


}


