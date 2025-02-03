package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ClientDto;
import org.example.models.Client;
import org.example.models.Hotel;
import org.example.services.ClientService;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    static Date date;
    static Hotel hotel;
    private Client initialClient;
    private ClientDto initialClientDto;

    @BeforeAll
    static void setup() {
        date = new Date();
        hotel = Hotel.builder()
                .hotel_id(1)
                .hotelAddress("Hotel Address")
                .hotelCity("Hotel City")
                .hotelCountry("Hotel Country")
                .hotelPhone("Hotel Phone")
                .hotelEmail("Hotel Email")
                .build();
    }

    @BeforeEach
    void setUp() {
        initialClient = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .age(24)
                .nrPersons(5)
                .checkIn(date)
                .checkOut(date)
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotel)
                .build();

        initialClientDto = ClientDto.fromClient(initialClient);
    }

    @Test
    void addClientTest() throws Exception {

        // given (if addClient returns void/nothing)
//        doAnswer(invocationOnMock -> {
//            System.out.println("Add client test" + invocationOnMock.getArgument(0));
//            assertInstanceOf(ClientDto.class, invocationOnMock.getArgument(0));
//            return null;
//        }).when(clientService).addClient(ArgumentMatchers.any(ClientDto.class));

        // given

        // when
        ResultActions response = mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialClientDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateClientTest() throws Exception {

    }
}