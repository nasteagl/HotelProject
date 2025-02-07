package org.example.dto;

import lombok.Builder;
import lombok.Value;
import org.example.enums.RoomTypeEnum;
import org.example.models.Room;
import org.example.models.RoomType;

import java.util.List;

@Value
@Builder
public class RoomTypeDto {
    Integer idRoomsType;
    RoomTypeEnum roomTypeEnum;

    static public RoomTypeDto fromRoomType(RoomType roomType){
        return   RoomTypeDto.builder()
                 .idRoomsType(roomType.getIdRoomsType())
                 .roomTypeEnum(roomType.getRoomTypeEnum())
                 .build();
    }

   static public RoomType fromRoomTypeDto(RoomTypeDto roomTypeDto){
        return RoomType.builder()
                .idRoomsType(roomTypeDto.getIdRoomsType())
                .roomTypeEnum(roomTypeDto.getRoomTypeEnum())
                .build();
   }
}
