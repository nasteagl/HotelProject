package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.HotelDto;
import org.example.models.Hotel;
import org.example.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = HotelController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Autowired
    private ObjectMapper objectMapper;

    private HotelDto initialHotelDto;

    @BeforeEach
    void setUp() {
        Hotel initialHotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelPhone("060000000")
                .hotelEmail("email@gmail.com")
                .build();

        initialHotelDto = HotelDto.fromHotel(initialHotel);
    }

    @Test
    void getHotelsTest() throws Exception    {
        when(hotelService.getHotels()).thenReturn(List.of(initialHotelDto));
        ResultActions response = mockMvc.perform(get("/api/hotel").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void getHotelTest() throws Exception {
        int hotelId = 1;
        when(hotelService.getHotel(hotelId)).thenReturn(initialHotelDto);
        ResultActions response = mockMvc.perform(get("/api/hotel/{hotel_id}", hotelId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialHotelDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCountry").value(initialHotelDto.getHotelCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCity").value(initialHotelDto.getHotelCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelAddress").value(initialHotelDto.getHotelAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelEmail").value(initialHotelDto.getHotelEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelPhone").value(initialHotelDto.getHotelPhone()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getHotelsAsNullTest() throws Exception {
        int hotelId = 1;
        when(hotelService.getHotels()).thenReturn(null);
        mockMvc.perform(get("/api/hotel/{hotel_id}", hotelId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void addHotelTest() throws Exception {

        given(hotelService.addHotel(ArgumentMatchers.any())).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        ResultActions resultActions = mockMvc.perform(post("/api/hotel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialHotelDto)));

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCountry").value(initialHotelDto.getHotelCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCity").value(initialHotelDto.getHotelCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelAddress").value(initialHotelDto.getHotelAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelEmail").value(initialHotelDto.getHotelEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelPhone").value(initialHotelDto.getHotelPhone()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateHotelTest() throws Exception {
        when(hotelService.updateHotel(initialHotelDto)).thenReturn(initialHotelDto);
        ResultActions response = mockMvc.perform(put("/api/hotel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialHotelDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCountry").value(initialHotelDto.getHotelCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCity").value(initialHotelDto.getHotelCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelAddress").value(initialHotelDto.getHotelAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelEmail").value(initialHotelDto.getHotelEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelPhone").value(initialHotelDto.getHotelPhone()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteHotelTest() throws Exception {
        int hotelId = 1;
        when(hotelService.getHotel(anyInt())).thenReturn(initialHotelDto);
        doNothing().when(hotelService).deleteHotel(hotelId);
        ResultActions resultActions = mockMvc.perform(delete("/api/hotel/{hotel_id}", hotelId)
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteHotelAsNullTest() throws Exception {
        int hotelId = 1;
        when(hotelService.getHotel(anyInt())).thenReturn(null);
        mockMvc.perform(delete("/api/hotel/{hotel_id}", hotelId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void patchHotelTest() throws Exception {
        int hotelId = 1;
        when(hotelService.getHotel(hotelId)).thenReturn(initialHotelDto);
        when(hotelService.patchHotel(hotelId, initialHotelDto)).thenReturn(initialHotelDto);
        ResultActions resultActions = mockMvc.perform(patch("/api/hotel/{hotel_id}", hotelId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialHotelDto)));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCountry").value(initialHotelDto.getHotelCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelCity").value(initialHotelDto.getHotelCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelAddress").value(initialHotelDto.getHotelAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelEmail").value(initialHotelDto.getHotelEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotelPhone").value(initialHotelDto.getHotelPhone()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void patchHotelAsNullTest() throws Exception {
        int hotelId = 1;
        when(hotelService.getHotel(hotelId)).thenReturn(null);
        when(hotelService.patchHotel(hotelId, initialHotelDto)).thenReturn(null);
        ResultActions resultActions = mockMvc.perform(patch("/api/hotel/{hotel_id}", hotelId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialHotelDto)));
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}