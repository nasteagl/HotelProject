package org.example.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.enums.RoomTypeEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms_type", schema = "hotel_schema") //num tab in baza de date
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rooms_type_id")
    private int idRoomsType;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="rooms_type_room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

//    public RoomType(RoomTypeBuilder builder) {
//        this.roomTypeEnum = builder.roomTypeEnum;
//    }
//
//    public static class RoomTypeBuilder {
//        private RoomTypeEnum roomTypeEnum;
//
//        public RoomTypeBuilder roomTypeBuilder(RoomTypeEnum roomTypeEnum) {
//            this.roomTypeEnum = roomTypeEnum;
//            return this;
//        }
//
//        public RoomType build() { return new RoomType(this); }
//    }

}