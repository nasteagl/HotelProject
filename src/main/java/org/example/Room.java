package org.example;
import java.util.*;
public class Room {
    private int id_room;
    private int floor;
    private int number;
    private int price;
    private int beds;
    private boolean reserved;
    List<RoomsType>rooms_types;

    Room(int id_room,int floor,int number,int price,int beds, List<RoomsType>rooms_types){
        this.id_room = id_room;
        this.floor = floor;
        this.number = number;
        this.price = price;
        this.beds = beds;
        this.reserved = false;
        this.rooms_types = new ArrayList<>();
    }

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


}
