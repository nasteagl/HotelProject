package org.example.services;

import org.example.dto.RoomDto;

import org.example.models.Room;
import org.example.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.modelMapper = new ModelMapper();
    }


    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class)).collect(Collectors.toList());
    }

//    public List<Room> getRooms() {
//        return roomRepository.findAll();
//    }
 ///Metoda cu clasa RoomDto
    public RoomDto getRoomById(Integer roomId) {
        return modelMapper.map(roomRepository.findById((roomId)), RoomDto.class);
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
//    public List<RoomDto> getRooms() {
//        return roomRepository.findAll()
//                .stream()
//                .map(room -> new RoomDto(
//                        room.getId_room(),
//                        room.getFloor(),
//                        room.getNumber(),
//                        room.getPrice(),
//                        room.getBeds(),
//                        room.isReserved()
//                )).collect(Collectors.toList());
//    }


}


