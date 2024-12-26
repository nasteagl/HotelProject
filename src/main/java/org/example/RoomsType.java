package org.example;
import lombok.Data;
import lombok.AllArgsConstructor;

enum RoomTypes {
    Standart,
    DoubleBed,
    SingleBed,
    Penthouse,
    FamilyRoom,
    PresidentialSuite
}

@Data
@AllArgsConstructor
public class RoomsType {
    private RoomTypes roomType;

    public RoomType(RoomTypeBuilder builder){
        this.roomType=builder.roomType;
    }


    public static class RoomTypeBuilder {
        private RoomTypes roomType;

        public RoomTypeBuilder roomTypeBuiler(RoomTypes roomType) {
            this.roomType = roomType;
            return this;
        }

        public RoomType build(){
            return new RoomType(this);
        }
    }





}

