package org.example;

import org.example.dto.RoomDto;
import org.example.enums.RoomTypeEnum;
import org.example.models.Room;
import org.example.models.RoomType;
import org.example.services.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class IntegrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetRooms() throws Exception {
        RoomTypeEnum roomType = RoomTypeEnum.Standard;
        Room initialRoom = Room.builder()
                .floor(3)
                .number(3)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(RoomType.builder().roomTypeEnum(roomType).build())
                .build();
        RoomDto room1 = RoomDto.fromRoom(initialRoom);
        Room anotherRoom = Room.builder()
                .floor(4)
                .number(4)
                .price(400)
                .beds(3)
                .reserved(true)
                .roomType(RoomType.builder().roomTypeEnum(RoomTypeEnum.DoubleBed).build())
                .build();
        RoomDto room2 = RoomDto.fromRoom(anotherRoom);
        List<RoomDto> rooms = Arrays.asList(room1, room2);
        when(roomService.getAllRooms()).thenReturn(rooms);
        mockMvc.perform(get("/api/room")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(rooms)));
    }
}
