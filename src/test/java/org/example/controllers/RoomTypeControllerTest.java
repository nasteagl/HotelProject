package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RoomTypeDto;
import org.example.enums.RoomTypeEnum;
import org.example.models.Room;
import org.example.models.RoomType;
import org.example.services.RoomTypeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(RoomTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class RoomTypeControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private RoomTypeService roomTypeService;
  @Autowired
  private ObjectMapper objectMapper;
  static Room room;
  static RoomType roomType;
  private RoomTypeDto roomTypeDto;

  @BeforeAll
  static void setup() {
      room = new Room();
      roomType = RoomType.builder()
              .idRoomsType(1)
              .roomTypeEnum(RoomTypeEnum.FamilyRoom)
              .rooms(List.of(room))
              .build();
  }
  @BeforeEach
  void setUp() {
      room=Room.builder()
              .floor(1)
              .number(1)
              .beds(1)
              .price(1)
              .reserved(true)
              .roomType(roomType)
              .build();
      roomTypeDto = RoomTypeDto.fromRoomType(roomType);
  }
    @Test
    void getAllRoomTypes() throws Exception {
        RoomType roomType =RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .build();
        roomTypeDto = RoomTypeDto.fromRoomType(roomType);
        when(roomTypeService.getAllRoomTypes()).thenReturn(List.of(roomTypeDto));
        ResultActions response=mockMvc.perform(get("/api/room_type").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void getRoomType() throws   Exception {
        RoomType roomType =RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .build();
        roomTypeDto = RoomTypeDto.fromRoomType(roomType);
        when(roomTypeService.getRoomTypeById(1)).thenReturn(roomTypeDto);
        ResultActions response = mockMvc.perform(get("/api/room_type/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomTypeDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idRoomsType").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void addRoomType() throws Exception {
        given(roomTypeService.addRoomType(ArgumentMatchers.any())).willAnswer(invocationOnMock ->  invocationOnMock.getArgument(0));
        //System.out.println(objectMapper.writeValueAsString(roomTypeDto));
        ResultActions response= mockMvc.perform(post("/api/room_type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomTypeDto)));
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(roomTypeDto)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomTypeEnum").value(roomTypeDto.getRoomTypeEnum().toString()))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void updateRoomType() throws Exception {
        roomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.SingleBed)
                .rooms(List.of(room))
                .build();
        when(roomTypeService.getRoomTypeById(1)).thenReturn(roomTypeDto);
       when(roomTypeService.patchRoomType(1,roomTypeDto)).thenReturn(roomTypeDto);
       ResultActions response= mockMvc.perform(patch("/api/room_type/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(roomTypeDto)));

       response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void patchRoomType() throws Exception {
        roomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.SingleBed)
                .rooms(List.of(room))
                .build();

        when(roomTypeService.getRoomTypeById(1)).thenReturn(roomTypeDto);
        when(roomTypeService.patchRoomType(1,roomTypeDto)).thenReturn(roomTypeDto);
        ResultActions response= mockMvc.perform(patch("/api/room_type/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomTypeDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void deleteRoomType() throws Exception {
//         when(roomTypeService.getRoomTypeById(1)).thenReturn(roomTypeDto);
       when(roomTypeService.getRoomTypeById(anyInt())).thenReturn(roomTypeDto);
       ResultActions response=mockMvc.perform(delete("/api/room_type/1")
               .contentType(MediaType.APPLICATION_JSON));

       response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void deleteRoomType_notFound() throws Exception {
        when(roomTypeService.getRoomTypeById(anyInt())).thenReturn(null);

        ResultActions response = mockMvc.perform(delete("/api/room_type/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void updateRoomType_notFound() throws Exception {
        when(roomTypeService.getRoomTypeById(anyInt())).thenReturn(null);

        ResultActions response = mockMvc.perform(patch("/api/room_type/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomTypeDto)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}