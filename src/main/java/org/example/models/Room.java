package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="room", schema = "hotel_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrementareee
    @Column(name="room_id")
    private int id_room;

    @Column(name="room_floor", nullable = false)
    private int floor;

    @Column(name="room_number", nullable = false, unique = true)
    private int number;

    @Column(name = "room_price", nullable = false)
    private int price;

    @Column(name="room_beds", nullable = false)
    private int beds;

    @Column(name="room_reserved", nullable = false)
    private boolean reserved;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="room_rooms_type_id")
    private RoomType roomType;

    public Room(RoomBuilder builder){
        this.id_room=builder.id_room;
        this.floor=builder.floor;
        this.number=builder.number;
        this.price=builder.price;
        this.beds=builder.beds;
        this.reserved=builder.reserved;
    }


    public static class RoomBuilder {
        private int id_room;
        private int floor;
        private int number;
        private int price;
        private int beds;
        private boolean reserved;

        public RoomBuilder setIdRoom(int id_room) {
          if(id_room >=0){
              throw new IllegalArgumentException("id_room must be greater than 0");
          }
            this.id_room = id_room;
            return this;
        }

        public RoomBuilder setFloor(int floor) {
            if(floor >=0){
                throw new IllegalArgumentException("floor must be greater than 0");
            }
            this.floor = floor;
            return this;
        }

        public RoomBuilder setNumber(int number) {
            if(number>0){
                throw new IllegalArgumentException("number must be greater than 0");
            }
            this.number = number;
            return this;
        }

        public RoomBuilder setPrice(int price) {
            if(price>0){
                throw new IllegalArgumentException("price must be greater than 0");
            }
            this.price = price;
            return this;
        }

        public RoomBuilder setBeds(int beds) {
            if(beds>0){
                throw new IllegalArgumentException("beds must be greater than 0");
            }
            this.beds = beds;
            return this;
        }

        public RoomBuilder setReserved(boolean reserved) {
            this.reserved = reserved;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
