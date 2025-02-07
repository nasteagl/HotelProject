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
//        Room rooms= Room.builder()
//                .floor(3)
//                .number(3)
//                .price(300)
//                .beds(2)
//                .reserved(false)
//                .roomType(roomTypeRepository.findById(1))
//                .build();

        RoomType bbb = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .rooms(null)
                .build();
        RoomTypeDto roomTypeDto = RoomTypeDto.fromRoomType(bbb);
        //   roomTypeService.addRoomType(roomTypeDto);
        when(roomTypeRepository.save(bbb)).thenReturn(bbb);
        roomTypeService.addRoomType(roomTypeDto);
        verify(roomTypeRepository).save(bbb);

    }

    @Test
    void patchRoomType() {
        OneroomType= RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .build();
        OneroomType=RoomTypeDto.fromRoomTypeDto(RoomTypeDto.fromRoomType(OneroomType));

        RoomTypeDto updatedRoomTypeDto = RoomTypeDto.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.DoubleBed)
                .build();

        RoomType roomTypeDtoNegativAndNull=RoomType.builder()
                .idRoomsType(null)
                .roomTypeEnum(null)
                .rooms(null)
                .build();
        when(roomTypeRepository.findById(roomTypeDto.getRoomTypeEnum().ordinal())).thenReturn(OneroomType);
        given(roomTypeService.addRoomType(roomTypeDto)).willReturn(roomTypeDto);


//        roomTypeService.addRoomType(roomTypeDto);
//        ArgumentCaptor<RoomType> captor = ArgumentCaptor.forClass(RoomType.class);
//        verify(roomTypeRepository).save(captor.capture());
//        RoomType roomType = captor.getValue();
//        assertEquals(roomTypeDto, roomType);
//
//        roomTypeService.patchRoomType(1, RoomTypeDto.fromRoomType(roomType));
//        verify(roomTypeRepository).save(captor.capture());
//        RoomType updatedRoomType = captor.getValue();
//        assertEquals(updatedRoomTypeDto, updatedRoomType);


    }
    @Test void deleteRoomType() {
        OneroomType= RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .build();
        OneroomType=RoomTypeDto.fromRoomTypeDto(RoomTypeDto.fromRoomType(OneroomType));
        roomTypeService.deleteRoomType(1);
        verify(roomTypeRepository).findById(OneroomType.getIdRoomsType());

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

}