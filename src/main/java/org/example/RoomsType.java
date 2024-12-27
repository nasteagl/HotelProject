package org.example;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class RoomsType {
    public enum RoomTypes {
        Standard,
        DoubleBed,
        SingleBed,
        Penthouse,
        FamilyRoom,
        PresidentialSuite
    }
    private RoomTypes roomType;
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
