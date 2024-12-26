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


    private Room(RoomBuilder builder){
        this.id_room=builder.id_room;
        this.floor=builder.floor;
        this.number=builder.number;
        this.price=builder.price;
        this.beds=builder.beds;
        this.reserved=builder.reserved;
        this.rooms_types=builder.rooms_types;
    }


    public static class RoomBuilder {
        private int id_room;
        private int floor;
        private int number;
        private int price;
        private int beds;
        private boolean reserved;
        private List<RoomsType> rooms_types;

        public RoomBuilder setIdRoom(int id_room) {
            this.id_room = id_room;
            return this;
        }

        public RoomBuilder setFloor(int floor) {
            this.floor = floor;
            return this;
        }

        public RoomBuilder setNumber(int number) {
            this.number = number;
            return this;
        }

        public RoomBuilder setPrice(int price) {
            this.price = price;
            return this;
        }

        public RoomBuilder setBeds(int beds) {
            this.beds = beds;
            return this;
        }

        public RoomBuilder setReserved(boolean reserved) {
            this.reserved = reserved;
            return this;
        }

        public RoomBuilder setRoomsTypes(List<RoomsType> rooms_types) {
            this.rooms_types = rooms_types;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }

    public String toString() {
        return "Rooms{" +
                "rooms_id=" + id_room +
                ",floor='" + floor + '\'' +
                ", number='" + number + '\'' +
                ", price=" + price +
                ", beds=" + beds +
                ", reserved=" + reserved +
                ", rooms type=" + rooms_types +

                '}';

    }

}
