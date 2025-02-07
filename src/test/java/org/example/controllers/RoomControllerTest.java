package org.example.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RoomDto;
import org.example.enums.RoomTypeEnum;
import org.example.models.Room;
import org.example.models.RoomType;
import org.example.services.RoomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(RoomController.class)
class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    static RoomType roomType;
    private RoomDto initialRoomDto;

    @BeforeAll
    static void setUp(){
        roomType = RoomType.builder()
                .roomTypeEnum(RoomTypeEnum.Standard)
                .build();
    }
    @BeforeEach
    public void setup(){
        Room initialRoom = Room.builder()
                .floor(3)
                .number(3)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .build();
        initialRoomDto = RoomDto.fromRoom(initialRoom);
    }

    @Test
    void getRoomsTest() throws Exception {
        when(roomService.getAllRooms()).thenReturn(List.of(initialRoomDto));
        ResultActions response = mockMvc.perform(get("/api/room")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void getRoomById() throws Exception {
        int roomId = 1;

        // when
        when(roomService.getRoomById(roomId)).thenReturn(initialRoomDto);

        ResultActions response = mockMvc.perform(get("/api/room/{roomId}", roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialRoomDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(initialRoomDto.getNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.floor").value(initialRoomDto.getFloor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beds").value(initialRoomDto.getBeds()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(initialRoomDto.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reserved").value(initialRoomDto.getReserved()))
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
    void getRoomAsNullTest() throws Exception {
        int roomId = 1;
        when(roomService.getRoomById(roomId)).thenReturn(null);
        mockMvc.perform(get("/api/room/1", roomId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
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
    void deleteRoomById_BadRequest() throws Exception {
        int invalidRoomId = -1;

        ResultActions response = mockMvc.perform(delete("/api/room/{roomId}", invalidRoomId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
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
        int roomId = 1;
        when(roomService.getRoomById(roomId)).thenReturn(null);
        when(roomService.patchRoom(roomId, initialRoomDto)).thenReturn(initialRoomDto);
        ResultActions response = mockMvc.perform(patch("/api/room/{roomId}", roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialRoomDto)));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }



}
