package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ClientDto;
import org.example.dto.HotelDto;
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
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
        Client initialClient = Client.builder()
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
    void getClientsTest() throws Exception {

        // when
        when(clientService.getClients()).thenReturn(List.of(initialClientDto));

        ResultActions response = mockMvc.perform(get("/api/client")
        .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void getClientTest() throws Exception {

        // given
        int clientId = 1;

        // when
        when(clientService.getClient(clientId)).thenReturn(initialClientDto);

        ResultActions response = mockMvc.perform(get("/api/client/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialClientDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value(initialClientDto.getFirstname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value(initialClientDto.getLastname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(initialClientDto.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nrPersons").value(initialClientDto.getNrPersons()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(initialClientDto.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(initialClientDto.getEmail()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getClientAsNullTest() throws Exception {

        // given
        int clientId = 1;

        // when
        when(clientService.getClient(clientId)).thenReturn(null);

        // then
        mockMvc.perform(get("/api/client/{clientId}", clientId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
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
        given(clientService.addClient(ArgumentMatchers.any())).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialClientDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value(initialClientDto.getFirstname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value(initialClientDto.getLastname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(initialClientDto.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nrPersons").value(initialClientDto.getNrPersons()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.checkIn").value(initialClientDto.getCheckIn().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.checkOut").value(initialClientDto.getCheckOut().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(initialClientDto.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(initialClientDto.getEmail()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.hotel").value(HotelDto.fromHotelDto(initialClientDto.getHotel())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateClientTest() throws Exception {

        // when
        when(clientService.updateClient(initialClientDto)).thenReturn(initialClientDto);

        ResultActions response = mockMvc.perform(put("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialClientDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value(initialClientDto.getFirstname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value(initialClientDto.getLastname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(initialClientDto.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nrPersons").value(initialClientDto.getNrPersons()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(initialClientDto.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(initialClientDto.getEmail()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteClientTest() throws Exception {

        // given
        int clientId = 1;

        // when
        when(clientService.getClient(anyInt())).thenReturn(initialClientDto);

        doNothing().when(clientService).deleteClient(clientId);

        ResultActions response = mockMvc.perform(delete("/api/client/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteClientAsNullTest() throws Exception {

        // given
        int clientId = 1;

        // when
        when(clientService.getClient(clientId)).thenReturn(null);

        // then
        mockMvc.perform(delete("/api/client/{clientId}", clientId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void patchClientTest() throws Exception {

        // given
        int clientId = 1;

        // when
        when(clientService.getClient(clientId)).thenReturn(initialClientDto);
        when(clientService.patchClient(clientId, initialClientDto)).thenReturn(initialClientDto);

        ResultActions response = mockMvc.perform(patch("/api/client/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialClientDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value(initialClientDto.getFirstname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value(initialClientDto.getLastname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(initialClientDto.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nrPersons").value(initialClientDto.getNrPersons()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(initialClientDto.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(initialClientDto.getEmail()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void patchClientAsNullTest() throws Exception {

        // given
        int clientId = 1;

        // when
        when(clientService.getClient(clientId)).thenReturn(null);
        when(clientService.patchClient(clientId, initialClientDto)).thenReturn(initialClientDto);

        ResultActions response = mockMvc.perform(patch("/api/client/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialClientDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}