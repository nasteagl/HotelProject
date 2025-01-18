package org.example.dto;


import lombok.*;
import org.example.models.Room;

@Data
@AllArgsConstructor
@Value
@Builder
public class RoomDto {
    private int id_room;
    private int floor;
    private int number;
    private int price;
    private int beds;
    private boolean reserved;
    private RoomTypeDto roomType;


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
                .reserved(roomDto.isReserved())
                .roomType(RoomTypeDto.fromRoomTypeDto(roomDto.getRoomType()))
                .build();
    }

}
