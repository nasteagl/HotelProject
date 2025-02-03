package org.example.controllers;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    static Hotel hotel;
    private Client initialClient;
    private ClientDto initialClientDto;

    @BeforeAll
    static void setup() {
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
                .client_id(1)
                .firstname("John")
                .lastname("Doe")
                .age(24)
                .nrPersons(5)
                .checkIn(new Date())
                .checkOut(new Date())
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotel)
                .build();

        initialClientDto = ClientDto.fromClient(initialClient);
    }


    @Test
    void addClientTest() throws Exception {

        // given
        //given(clientService.addClient(ArgumentMatchers.any())).willAnswer(invocationOnMock -> invocationOnMock.getArguments());

        // when
        ResultActions response = mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(initialClientDto.toString()));

        // then
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}