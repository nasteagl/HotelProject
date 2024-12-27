package org.example;
import java.util.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto incrementareee
    @Column(name="id_room")
    private int id_room;

    @Column(name="floor", nullable = false)
    private int floor;

    @Column(name="number", nullable = false, unique = true)
    private int number;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name="beds", nullable = false)
    private int beds;

    @Column(name="reserved", nullable = false)
    private boolean reserved;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="roomType")
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



}
