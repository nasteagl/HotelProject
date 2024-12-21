package org.example;
import java.util.*;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Room {
    private int id_room;
    private int floor;
    private int number;
    private int price;
    private int beds;
    private boolean reserved;
    List<RoomsType>rooms_types;






}
