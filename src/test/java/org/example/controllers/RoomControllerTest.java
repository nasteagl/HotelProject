package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RoomDto;
import org.example.dto.RoomTypeDto;
import org.example.enums.RoomTypeEnum;
import org.example.models.Room;
import org.example.models.RoomType;
import org.example.repositories.RoomRepository;
import org.example.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(RoomController.class)
class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomService roomService;
    @Autowired

    private ObjectMapper objectMapper;
    private RoomDto roomDto;

    static RoomType roomType;

    @BeforeEach
    public void setup(){
        roomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.Standard)
                .build();
    }

    @Test
    void getRooms() throws Exception {

        RoomType roomType =RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .build();

        Room room = Room.builder()
                .id_room(1)
                .floor(3)
                .number(3)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();

        roomDto= RoomDto.fromRoom(room);
        when(roomService.getAllRooms()).thenReturn(List.of(roomDto));
        ResultActions response = mockMvc.perform(get("/api/room").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    void getRoomById() throws Exception {
        int roomId = 1;
        Room roomDto=Room.builder()
                .floor(1)
                .number(1)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();

        when(roomService.getRoomById(1)).thenReturn(RoomDto.fromRoom(roomDto));
        ResultActions response = mockMvc.perform(get("/api/room/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.floor").value(roomDto.getFloor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(roomDto.getNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(roomDto.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beds").value(roomDto.getBeds()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reserved").value(roomDto.getReserved()))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void addRoom() throws Exception {
        Room roomDto=Room.builder()
                .floor(1)
                .number(1)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();

        when(roomService.getRoomById(1)).thenReturn(RoomDto.fromRoom(roomDto));
        when(roomService.addRoom(RoomDto.fromRoom(roomDto))).thenReturn(RoomDto.fromRoom(roomDto));
        ResultActions response = mockMvc.perform(post("/api/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.floor").value(roomDto.getFloor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(roomDto.getNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(roomDto.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beds").value(roomDto.getBeds()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reserved").value(roomDto.getReserved()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateRoom()throws Exception {

        Room roomDto=Room.builder()
                .id_room(1)
                .floor(2)
                .number(1)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();

        when(roomService.updateRoom(RoomDto.fromRoom(roomDto))).thenReturn(RoomDto.fromRoom(roomDto));

        ResultActions response = mockMvc.perform(put("/api/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto)));


        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.floor").value(roomDto.getFloor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(roomDto.getNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(roomDto.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beds").value(roomDto.getBeds()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reserved").value(roomDto.getReserved()))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void deleteRoomById() throws Exception {
        Room roomD=Room.builder()
                .id_room(1)
                .floor(1)
                .number(1)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();
        Room roomDto=Room.builder()
                .id_room(1)
                .floor(2)
                .number(1)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();

        when(roomService.getRoomById(anyInt())).thenReturn(RoomDto.fromRoom(roomDto));
        ResultActions response = mockMvc.perform(delete("/api/room/1")
                .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    void patchRoom() throws Exception {

        Room roomDto=Room.builder()
                .id_room(1)
                .floor(2)
                .number(1)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();
        when(roomService.getRoomById(1)).thenReturn(RoomDto.fromRoom(roomDto));
        when(roomService.patchRoom(1, RoomDto.fromRoom(roomDto))).thenReturn(RoomDto.fromRoom(roomDto));
        ResultActions response= mockMvc.perform(patch("/api/room/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.floor").value(roomDto.getFloor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(roomDto.getNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(roomDto.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beds").value(roomDto.getBeds()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reserved").value(roomDto.getReserved()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void patchIfRoomIsNull() throws Exception {
        Room roomDto=Room.builder()
                .id_room(null)
                .floor(null)
                .number(null)
                .price(null)
                .beds(null)
                .reserved(null)
                .roomType(roomType)
                .build();
        when(roomService.getRoomById(anyInt())).thenReturn(null);
        when(roomService.patchRoom(1, RoomDto.fromRoom(roomDto))).thenReturn(null);

        ResultActions response= mockMvc.perform(patch("/api/room/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto)));
        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
