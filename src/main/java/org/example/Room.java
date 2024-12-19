package org.example;
import java.util.*;
public class Room {
    private int id_room;
    private int floor;
    private int number;
    private int price;
    private int beds;
    private String room_type;
    private boolean reserved;
    List<RoomsType>rooms_types;

    public int getId_room() {
        return id_room;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumber() {
        return number;
    }

    public int getBeds() {
        return beds;
    }

    public int getPrice() {
        return price;
    }

    public String getRoom_type() {
        return room_type;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
}
