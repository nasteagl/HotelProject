package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.models.Room;
import org.example.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getAllRooms() {
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
}


