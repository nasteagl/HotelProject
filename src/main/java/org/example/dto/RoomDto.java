package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private int id_room;
    private int floor;
    private int number;
    private int price;
    private int beds;
    private boolean reserved;
}
