package org.example;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms_type") //num tab in baza de date
public class RoomsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rooms_type")
    private int idRoomsType;

    @Enumerated(EnumType.STRING) //  enum-ul va fi salvat ca string
    @Column(name = "room_type", nullable = false)
    private RoomTypes roomType;



    public enum RoomTypes {
        Standart,
        DoubleBed,
        SingleBed,
        Penthouse,
        FamilyRoom,
        PresidentialSuite
    }


    public RoomsType(RoomTypeBuilder builder) {
        this.roomType = builder.roomType;
    }


    public static class RoomTypeBuilder {
        private RoomTypes roomType;

        public RoomTypeBuilder roomTypeBuilder(RoomTypes roomType) {
            this.roomType = roomType;
            return this;
        }

        public RoomsType build() {
            return new RoomsType(this);
        }
    }

}
