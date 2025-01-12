package org.example.models;

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

    public enum RoomTypes {
        Standart,
        DoubleBed,
        SingleBed,
        Penthouse,
        FamilyRoom,
        PresidentialSuite
    }

    @Enumerated(EnumType.STRING) //  enum-ul va fi salvat ca string
    @Column(name = "current_type", nullable = false)
    private RoomTypes roomType;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="id_room")
    private Room room;


    public RoomsType(RoomTypeBuilder builder) {
        this.roomType = builder.roomType;
    }

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
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