package org.example.services;

import org.example.dto.RoomDto;
import org.example.dto.RoomTypeDto;
import org.example.enums.RoomTypeEnum;
import org.example.models.Room;
import org.example.models.RoomType;
import org.example.repositories.RoomRepository;
import org.example.repositories.RoomTypeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomTypeServiceTest {
  @Mock
  private RoomTypeRepository roomTypeRepository;
  @InjectMocks
  private RoomTypeService roomTypeService;
  @Mock
  private RoomService roomService;
  private RoomType OneroomType;
  static RoomTypeDto roomTypeDto;
  static RoomTypeDto OneroomTypeDto;
  //private  RoomTypeDto SecondRoomType;
  List<Room> rooms;

  @BeforeAll
  static void  setUpAll(){
      Room rooming = Room.builder()
              .floor(3)
              .number(3)
              .price(300)
              .beds(2)
              .reserved(false)
              .build();
  }
  @BeforeEach
  void setUp() {
      OneroomType= RoomType.builder()
              .idRoomsType(1)
              .roomTypeEnum(RoomTypeEnum.FamilyRoom)
              .build();
      OneroomTypeDto=RoomTypeDto.fromRoomType(OneroomType);

  }
  @Test
  void getAllRoomTypes() {
         roomTypeService.getAllRoomTypes();
         verify(roomTypeRepository).findAll();
    }

    @Test
    void getRoomTypeById() {
      when(roomTypeRepository.findById(OneroomType.getIdRoomsType())).thenReturn(OneroomType);
      roomTypeService.getRoomTypeById(1);
      verify(roomTypeRepository).findById(OneroomType.getIdRoomsType());
    }

    @Test
    void addRoomType() {
        RoomType bbb = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.DoubleBed)
                .rooms(null)
                .build();
        RoomTypeDto roomTypeDto = RoomTypeDto.fromRoomType(bbb);

        // Mock save behavior
        when(roomTypeRepository.save(bbb)).thenReturn(bbb);

        roomTypeService.addRoomType(roomTypeDto);

        // Verifică dacă metoda `save()` a fost apelată cu obiectul corect
        ArgumentCaptor<RoomType> captor = ArgumentCaptor.forClass(RoomType.class);
        verify(roomTypeRepository).save(captor.capture());
        RoomType capturedRoomType = captor.getValue();

        assertEquals(bbb.getIdRoomsType(), capturedRoomType.getIdRoomsType());
        assertEquals(bbb.getRoomTypeEnum(), capturedRoomType.getRoomTypeEnum());
    }

    @Test
    void patchRoomType() {
        RoomType OneroomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.DoubleBed)
                .build();
        RoomTypeDto updatedRoomTypeDto = RoomTypeDto.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.DoubleBed)
                .build();

        when(roomTypeRepository.findById(1)).thenReturn(OneroomType);

        // Test valid patch (update RoomType)
        roomTypeService.patchRoomType(1, updatedRoomTypeDto);
        ArgumentCaptor<RoomType> captor = ArgumentCaptor.forClass(RoomType.class);
        verify(roomTypeRepository).save(captor.capture());
        RoomType capturedRoomType = captor.getValue();

        // Verifică dacă actualizarea a avut loc
        assertEquals(updatedRoomTypeDto.getRoomTypeEnum(), capturedRoomType.getRoomTypeEnum());

        // Test patch with null roomTypeDto
        assertThrows(NullPointerException.class, () -> roomTypeService.patchRoomType(1, null), "roomTypeDto is null");

        // Test patch with non-existing roomType (id doesn't exist)
        when(roomTypeRepository.findById(9999)).thenReturn(null);
        assertThrows(NullPointerException.class, () -> roomTypeService.patchRoomType(9999, updatedRoomTypeDto), "roomType is null");
    }


    @Test
    void deleteRoomType() {
        RoomType OneroomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .build();

        when(roomTypeRepository.findById(1)).thenReturn(OneroomType);


        roomTypeService.deleteRoomType(1);
        verify(roomTypeRepository).deleteByIdRoomType(1);
    }


    @Test
    void updateRoomType() {
        OneroomType= RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .rooms(null)
                .build();


        when(roomTypeRepository.findById(OneroomType.getIdRoomsType())).thenReturn(OneroomType);
        roomTypeService.patchRoomType(1, RoomTypeDto.fromRoomType(OneroomType));
        verify(roomTypeRepository).findById(OneroomType.getIdRoomsType());

    }

    @Test
    void updateRoomTypeS() {
        RoomType OneroomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .rooms(null)
                .build();

        RoomTypeDto roomTypeDto = RoomTypeDto.fromRoomType(OneroomType);

        when(roomTypeRepository.findById(1)).thenReturn(OneroomType);

        // Test update room type
        roomTypeService.updateRoomType(1, roomTypeDto);

        ArgumentCaptor<RoomType> captor = ArgumentCaptor.forClass(RoomType.class);
        verify(roomTypeRepository).save(captor.capture());
        RoomType updatedRoomType = captor.getValue();

        // Verifică dacă roomType a fost salvat corect
        assertEquals(roomTypeDto.getIdRoomsType(), updatedRoomType.getIdRoomsType());
        assertEquals(roomTypeDto.getRoomTypeEnum(), updatedRoomType.getRoomTypeEnum());
    }

}