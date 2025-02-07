package org.example.services;
import org.example.dto.RoomDto;
import org.example.dto.RoomTypeDto;
import static org.junit.jupiter.api.Assertions.*;
import org.example.enums.RoomTypeEnum;
import org.example.models.Room;
import org.example.models.RoomType;
import org.example.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    private Room initialroom;
    private RoomDto initialroomDto;

    static RoomType roomType;

    @BeforeAll
    static void setup() {
        roomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.Standard)
                .build();
    }


    @BeforeEach
    void setUp() {
        initialroom = Room.builder()
                .id_room(1)
                .floor(3)
                .number(5)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();
        initialroomDto = RoomDto.fromRoom(initialroom);
    }

    @Test
    void getAllRooms() {
        roomService.getAllRooms();
        verify(roomRepository, times(1)).findAll();

    }
    @Test
    void getRoomByIdWhenRoomDoesNotExist() {
        when(roomRepository.findById(9999)).thenReturn(null);
        assertNull(roomService.getRoomById(9999), "Room should not be found");
    }

    @Test
    void getRoomById() {
        given(roomRepository.findById(anyInt())).willReturn(initialroom);
        roomService.getRoomById(1);
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void addRoom() {
        given(roomRepository.save(any(Room.class))).willReturn(initialroom);
        roomService.addRoom(initialroomDto);
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomArgumentCaptor.capture()); // Corrected verify usage
        Room capturedRoom = roomArgumentCaptor.getValue();

        assertEquals(initialroom, capturedRoom);
    }

    @Test
    void getRoomAsNullTest(){
        given(roomRepository.findById(1)).willReturn(null);
        roomService.getRoomById(1);
        verify(roomRepository).findById(1);
    }

    @Test
    void updateRoom() {

        given(roomRepository.save(initialroom)).willReturn(initialroom);

        Room updatedRoom = Room.builder()
                .id_room(1)
                .floor(5)
                .number(66)
                .price(400)
                .beds(2)
                .reserved(false)
                .roomType(initialroom.getRoomType())
                .build();

        given(roomRepository.update(updatedRoom)).willReturn(updatedRoom);

        // when (save initialRoom)
        roomService.addRoom(initialroomDto);

        // then (save initialRoom)
        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomCaptor.capture());
        Room capturedRoom = roomCaptor.getValue();
        assertEquals(initialroom, capturedRoom);

        // when (update updatedRoom)
        roomService.updateRoom(RoomDto.fromRoom(updatedRoom));

        // then (update updatedRoom)
        ArgumentCaptor<Room> roomCaptor2 = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).update(roomCaptor2.capture());
        Room capturedRoom2 = roomCaptor2.getValue();
        assertEquals(updatedRoom, capturedRoom2);
    }

    @Test
    void deleteRoom() {
        given(roomRepository.save(any(Room.class))).willReturn(initialroom);

        // when (save Room)
        roomService.addRoom(RoomDto.fromRoom(initialroom));

        // then (save Room)
        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomCaptor.capture());
        Room capturedRoom = roomCaptor.getValue();
        assertEquals(initialroom, capturedRoom);

        // when (delete Room)
        roomService.deleteRoom(1);

        // then (delete Room)
        verify(roomRepository).deleteById(1);

    }

    @Test
    void patchRoomTest() {

        given(roomRepository.save(initialroom)).willReturn(initialroom);
        given(roomRepository.findById(anyInt())).willReturn(initialroom);

        Room actualRoomSame = initialroom;

        Room actualRoomNullValue = Room.builder()
                .id_room(1)
                .floor(null)
                .number(null)
                .price(null)
                .beds(null)
                .reserved(null)
                .roomType(roomType)
                .build();

        Room actualRoomAllWrongValue = Room.builder()
                .id_room(1)
                .floor(-1)
                .number(-1)
                .price(-1)
                .beds(-1)
                .reserved(false)
                .roomType(roomType)
                .build();

        Room actualRoomWrongPriceNumberBeds = Room.builder()
                .id_room(1)
                .floor(2)
                .number(-2)
                .price(-100)
                .beds(-1)
                .reserved(false)
                .roomType(roomType)
                .build();

        // when (save initialRoom)
        roomService.addRoom(initialroomDto);

        // then (save initialRoom)
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomArgumentCaptor.capture());
        Room capturedRoom = roomArgumentCaptor.getValue();
        assertEquals(initialroom, capturedRoom);

        // (patch Room with same values)
        roomService.patchRoom(1, RoomDto.fromRoom(actualRoomSame));

        // then (patch Room with same values)
        assertAll(
                () -> assertEquals(1, actualRoomSame.getId_room(), "Room ID is incorrect"),
                () -> assertEquals(3, actualRoomSame.getFloor(), "Floor is incorrect"),
                () -> assertEquals(300, actualRoomSame.getPrice(), "Price is incorrect"),
                () -> assertEquals(2, actualRoomSame.getBeds(), "Beds count is incorrect"),
                () -> assertEquals(false, actualRoomSame.getReserved(), "Reserved status is incorrect"),
                () -> assertEquals(roomType, actualRoomSame.getRoomType(), "RoomType is incorrect")
        );

        // when (patch Room with null values)
        roomService.patchRoom(1, RoomDto.fromRoom(actualRoomNullValue));

        // then (patch Room with null values)
        assertAll(
                () -> assertNull(actualRoomNullValue.getFloor(), "Room Floor is not null"),
                () -> assertNull(actualRoomNullValue.getNumber(), "Room Number is not null"),
                () -> assertNull(actualRoomNullValue.getPrice(), "Price is not null"),
                () -> assertNull(actualRoomNullValue.getBeds(), "Beds is not null"),
                () -> assertNull(actualRoomNullValue.getReserved(), "Reserved status is not null")
        );

        // when (patch Room with wrong values)
        roomService.patchRoom(1, RoomDto.fromRoom(actualRoomAllWrongValue));

        // then (patch Room with wrong values)
        assertAll(
                () -> assertEquals(-1, actualRoomAllWrongValue.getFloor(), "Floor is negative"),
                () -> assertEquals(-1, actualRoomAllWrongValue.getNumber(), "Number is negative"),
                () -> assertEquals(-1, actualRoomAllWrongValue.getPrice(), "Price is negative"),
                () -> assertEquals(-1, actualRoomAllWrongValue.getBeds(), "Beds count is negative"),
                () -> assertEquals(false, actualRoomAllWrongValue.getReserved(), "Reserved status is incorrect")
        );

        // when (patch Room with wrong price, number, and beds values)
        roomService.patchRoom(1, RoomDto.fromRoom(actualRoomWrongPriceNumberBeds));

        // then (patch Room with wrong price, number, and beds values)
        assertAll(
                () -> assertEquals(-2, actualRoomWrongPriceNumberBeds.getNumber(), "Room Number is incorrect"),
                () -> assertEquals(-100, actualRoomWrongPriceNumberBeds.getPrice(), "Room Price is incorrect"),
                () -> assertEquals(-1, actualRoomWrongPriceNumberBeds.getBeds(), "Beds count is incorrect")
        );

        // when (patch Room with valid values)
        Room actualRoomValid = Room.builder()
                .id_room(1)
                .floor(4)
                .number(101)
                .price(350)
                .beds(3)
                .reserved(true)
                .roomType(roomType)
                .build();
        roomService.patchRoom(1, RoomDto.fromRoom(actualRoomValid));

        // then (patch Room with valid values)
        assertAll(
                () -> assertEquals(4, actualRoomValid.getFloor(), "Floor should be 4"),
                () -> assertEquals(101, actualRoomValid.getNumber(), "Room Number should be 101"),
                () -> assertEquals(350, actualRoomValid.getPrice(), "Price should be 350"),
                () -> assertEquals(3, actualRoomValid.getBeds(), "Beds count should be 3"),
                () -> assertTrue(actualRoomValid.getReserved(), "Reserved status should be true")
        );

        // when (patch Room with invalid ID)
        given(roomRepository.findById(9999)).willReturn(null); // Simulează cazul când camera nu există


        // then (patch Room with invalid ID)
        assertThrows(IllegalArgumentException.class, () -> roomService.patchRoom(9999, RoomDto.fromRoom(actualRoomValid)), "Room with ID 9999 not found");
    }




    @Test
    void getRoomsAsNullTest() {
        given(roomRepository.findById(1)).willReturn(null);
        roomService.getRoomById(1);
        verify(roomRepository).findById(1);
    }

    @Test
    void patchRoomThrowsExceptionWhenRoomNotFound() {
        given(roomRepository.findById(anyInt())).willReturn(null);

        assertThrows(IllegalArgumentException.class, () -> roomService.patchRoom(1, initialroomDto));
    }

    @Test
    void patchRoomAsNullTest() {
        given(roomRepository.save(initialroom)).willReturn(initialroom);
        given(roomRepository.findById(anyInt())).willReturn(null);

        roomService.addRoom(initialroomDto);

        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomArgumentCaptor.capture());
        Room capturedRoom = roomArgumentCaptor.getValue();
        assertEquals(initialroom, capturedRoom);

        assertThrows(IllegalArgumentException.class, () -> roomService.patchRoom(1, RoomDto.fromRoom(initialroom)));
        verify(roomRepository).save(roomArgumentCaptor.capture());
    }

    @Test
    void deleteRoomTest() {
        doNothing().when(roomRepository).deleteById(anyInt());
        roomService.deleteRoom(1);
        verify(roomRepository, times(1)).deleteById(1);
    }

    @Test
    void getRoomByIdNotFound() {
        when(roomRepository.findById(anyInt())).thenReturn(null);
        assertNull(roomService.getRoomById(1));
    }
    @Test
    void testGetRoomByIdReturnsRoomDtoWhenRoomExists() {


    }

}