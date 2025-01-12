package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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


//    public enum RoomTypes {
//        Standart,
//        DoubleBed,
//        SingleBed,
//        Penthouse,
//        FamilyRoom,
//        PresidentialSuite
//    }

    @Enumerated(EnumType.STRING) //  enum-ul va fi salvat ca string
    private RoomTypeEnum roomsTypeEnum;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="rooms_type_room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public RoomType(RoomTypeBuilder builder) {
        this.roomsTypeEnum = builder.roomsTypeEnum;
    }

    public static class RoomTypeBuilder {
        private RoomTypeEnum roomsTypeEnum;

        public RoomTypeBuilder roomTypeBuilder(RoomTypeEnum roomsTypeEnum) {
            this.roomsTypeEnum=roomsTypeEnum;
            return this;
        }

        public RoomType build() { return new RoomType(this); }
    }

}