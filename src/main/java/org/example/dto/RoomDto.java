package org.example.dto;

import lombok.*;
import org.example.models.Room;

@Value
@Builder
public class RoomDto {
    Integer id_room;
    Integer floor;
    Integer number;
    Integer price;
    Integer beds;
    Boolean reserved;
    RoomTypeDto roomType;

    //Entity->Dto
    static public RoomDto fromRoom(Room room) {
       return RoomDto.builder().
               id_room(room.getId_room())
                .floor(room.getFloor())
                .number(room.getNumber())
               .price(room.getPrice())
               .beds(room.getBeds())
               .reserved(room.getReserved())
               .roomType(RoomTypeDto.fromRoomType(room.getRoomType()))
               .build();

    }

    //Dto -> Entity pentru metoda post (sa se creezi obj room si apoi sa fie schimbat in Dto)
    static public Room fromRoomDto(RoomDto roomDto) {
        return Room.builder()
                .id_room(roomDto.getId_room())
                .floor(roomDto.getFloor())
                .number(roomDto.getNumber())
                .price(roomDto.getPrice())
                .beds(roomDto.getBeds())
                .reserved(roomDto.getReserved())
                .roomType(RoomTypeDto.fromRoomTypeDto(roomDto.getRoomType()))
                .build();
    }

}
