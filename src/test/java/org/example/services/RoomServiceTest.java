package org.example.services;
import org.example.dto.RoomDto;
import org.example.dto.RoomTypeDto;
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
    void getRoomById() {
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

    ;

    @Test
    void updateRoom() {

        Room updatedRoom = Room.builder()
                .id_room(1)
                .floor(5)
                .number(66)
                .price(400)
                .beds(2)
                .reserved(false)
                .roomType(initialroom.getRoomType())
                .build();

        when(roomRepository.findById(initialroom.getId_room())).thenReturn(initialroom);
        when(roomRepository.save(any(Room.class))).thenReturn(updatedRoom);

        roomService.patchRoom(1, RoomDto.fromRoom(updatedRoom));

        verify(roomRepository, times(1)).findById(initialroom.getId_room());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void deleteRoom() {
        roomService.deleteRoom(1);
        verify(roomRepository, times(1)).deleteById(1);

    }

    @Test
    void patchRoom() {
        Room roomPatch = Room.builder()
                .id_room(1)
                .floor(3)
                .number(3)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(initialroom.getRoomType())  // Se folosește roomType existent
                .build();

        when(roomRepository.findById(1)).thenReturn(initialroom);
        when(roomRepository.save(any(Room.class))).thenReturn(roomPatch);

        RoomDto updatedDto = RoomDto.builder()
                .id_room(1)
                .floor(4)
                .number(5)
                .price(400)
                .beds(4)
                .reserved(false)
                .build();

        roomService.patchRoom(1, updatedDto);

        verify(roomRepository, times(1)).findById(1);
        verify(roomRepository, times(1)).save(any(Room.class));
    }
}