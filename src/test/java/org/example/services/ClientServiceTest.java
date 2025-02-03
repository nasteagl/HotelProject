package org.example.services;

import org.example.dto.ClientDto;
import org.example.models.Client;
import org.example.models.Hotel;
import org.example.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
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
    void getClientsTest() {

        // when
        clientService.getClients();

        // then
        verify(clientRepository).findAllClients();
    }

    @Test
    void getClientTest() {

        // given
        given(clientRepository.findByIdClient(anyInt())).willReturn(
                Client.builder()
                    .firstname("John")
                    .lastname("Doe")
                    .age(24)
                    .nrPersons(5)
                    .checkIn(new Date())
                    .checkOut(new Date())
                    .phoneNumber("56327495")
                    .email("john@doe.com")
                    .hotel(hotel)
                    .build()
        );

        // when
        clientService.getClient(1);

        // then
        verify(clientRepository).findByIdClient(1);
    }

    @Test
    void getClientAsNullTest() {

        // given
        given(clientRepository.findByIdClient(1)).willReturn(null);

        // when
        clientService.getClient(1);

        // then
        verify(clientRepository).findByIdClient(1);
    }

    @Test
    void addClientTest() {

        // when
        clientService.addClient(initialClientDto);

        // then
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).saveClient(clientCaptor.capture());
        Client capturedClient = clientCaptor.getValue();
        assertEquals(initialClient, capturedClient);
    }

    @Test
    void updateClientTest() {

        // given
        Client actualClient = Client.builder()
                .client_id(1)
                .firstname("Boris")
                .lastname("Conan")
                .age(32)
                .nrPersons(4)
                .checkIn(new Date())
                .checkOut(new Date())
                .phoneNumber("58492031")
                .email("boris@conan.com")
                .hotel(hotel)
                .build();

        // when (save initialClient)
        clientService.addClient(initialClientDto);

        // then (save initialClient)
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).saveClient(clientCaptor.capture());
        Client capturedClient = clientCaptor.getValue();
        assertEquals(initialClient, capturedClient);

        // when (update actualClient)
        clientService.updateClient(ClientDto.fromClient(actualClient));

        // then (update actualClient)
        ArgumentCaptor<Client> clientCaptor2 = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).updateClient(clientCaptor2.capture());
        Client capturedClient2 = clientCaptor2.getValue();
        assertEquals(actualClient, capturedClient2);
    }

    @Test
    void deleteClientTest() {

        // given
        Client actualClient = Client.builder()
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

        // when (save Client)
        clientService.addClient(ClientDto.fromClient(actualClient));

        // then (save Client)
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).saveClient(clientCaptor.capture());
        Client capturedClient = clientCaptor.getValue();
        assertEquals(actualClient, capturedClient);

        // when (delete Client)
        clientService.deleteClient(1);

        // then (delete Client)
        verify(clientRepository).deleteClientById(1);
    }

    @Test
    void patchClientTest() {

        // given
        given(clientRepository.findByIdClient(anyInt())).willReturn(
                Client.builder()
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
                        .build()
        );

        Client actualClientSameVals = Client.builder()
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

        Client actualClientNullVals = Client.builder()
                .client_id(null)
                .firstname(null)
                .lastname(null)
                .age(null)
                .nrPersons(null)
                .checkIn(null)
                .checkOut(null)
                .phoneNumber(null)
                .email(null)
                .hotel(hotel)
                .build();

        Client actualClientAllWrongVals = Client.builder()
                .client_id(1)
                .firstname("")
                .lastname("")
                .age(-1)
                .nrPersons(-1)
                .checkIn(null)
                .checkOut(null)
                .phoneNumber("")
                .email("")
                .hotel(hotel)
                .build();

        Client actualClientWrongAgeAndNrPersonsVals = Client.builder()
                .client_id(1)
                .firstname("")
                .lastname("")
                .age(130)
                .nrPersons(13)
                .checkIn(null)
                .checkOut(null)
                .phoneNumber("")
                .email("")
                .hotel(hotel)
                .build();

        // when (saveInitialClient)
        clientService.addClient(initialClientDto);

        // then (saveInitialClient)
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).saveClient(clientCaptor.capture());
        Client capturedClient = clientCaptor.getValue();
        assertEquals(initialClient, capturedClient);

        // when (patch Client with same values)
        clientService.patchClient(1, ClientDto.fromClient(actualClientSameVals));

        // then (patch Client with same values)
        assertAll(
                () -> assertEquals("John", actualClientSameVals.getFirstname(), "Firstname is incorrect"),
                () -> assertEquals("Doe", actualClientSameVals.getLastname(), "Lastname is incorrect"),
                () -> assertEquals(24, actualClientSameVals.getAge(), "Age is incorrect"),
                () -> assertEquals(5, actualClientSameVals.getNrPersons(), "Persons is incorrect"),
//                () -> assertEquals(new Date(), actualClientSameVals.getCheckIn(), "CheckIn is incorrect"),
//                () -> assertEquals(new Date(), actualClientSameVals.getCheckOut(), "CheckOut is incorrect"),
                () -> assertEquals("56327495", actualClientSameVals.getPhoneNumber(), "PhoneNumber is incorrect"),
                () -> assertEquals("john@doe.com", actualClientSameVals.getEmail(), "Email is incorrect"),
                () -> assertEquals(hotel, actualClientSameVals.getHotel(), "Hotel is incorrect")
        );

        // when (patch Client with null values)
        clientService.patchClient(1, ClientDto.fromClient(actualClientNullVals));

        // then (patch Client with null values)
        assertAll(
                () -> assertNull(actualClientNullVals.getFirstname(), "Firstname is not null"),
                () -> assertNull(actualClientNullVals.getLastname(), "Lastname is not null"),
                () -> assertNull(actualClientNullVals.getAge(), "Age is not null"),
                () -> assertNull(actualClientNullVals.getNrPersons(), "Persons is not null"),
//                () -> assertNull(actualClientNullVals.getCheckIn(), "CheckIn is not null"),
//                () -> assertNull(actualClientNullVals.getCheckOut(), "CheckOut is not null"),
                () -> assertNull(actualClientNullVals.getPhoneNumber(), "PhoneNumber is not null"),
                () -> assertNull(actualClientNullVals.getEmail(), "Email is not null")
        );

        // when (patch Client with wrong values)
        clientService.patchClient(1, ClientDto.fromClient(actualClientAllWrongVals));

        // then (patch Client with wrong values)
        assertAll(
                () -> assertEquals("", actualClientAllWrongVals.getFirstname(), "Firstname is not empty"),
                () -> assertEquals("", actualClientAllWrongVals.getLastname(), "Lastname is not empty"),
                () -> assertEquals(-1, actualClientAllWrongVals.getAge(), "Age is lower than 0"),
                () -> assertEquals(-1, actualClientAllWrongVals.getNrPersons(), "Persons is lower than 0"),
                () -> assertEquals("", actualClientAllWrongVals.getPhoneNumber(), "PhoneNumber is not empty"),
                () -> assertEquals("", actualClientAllWrongVals.getEmail(), "Email is not empty")
        );

        // when (patch Client with age and nrPersons wrong values)
        clientService.patchClient(1, ClientDto.fromClient(actualClientWrongAgeAndNrPersonsVals));

        // then (patch Client with age and nrPersons wrong values)
        assertAll(
                () -> assertEquals(130, actualClientWrongAgeAndNrPersonsVals.getAge(), "Age is higher than 120"),
                () -> assertEquals(13, actualClientWrongAgeAndNrPersonsVals.getNrPersons(), "Persons is higher than 10")
        );
    }

    @Test
    void patchClientAsNullTest() {

        // given
        given(clientRepository.findByIdClient(anyInt())).willReturn(null);

        // when (saveInitialClient)
        clientService.addClient(initialClientDto);

        // then (saveInitialClient)
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).saveClient(clientCaptor.capture());
        Client capturedClient = clientCaptor.getValue();
        assertEquals(initialClient, capturedClient);

        // when

        // then (check if the exception is thrown and the mock is never used)
        assertThrows(NullPointerException.class, () -> clientService.patchClient(1, initialClientDto), "Client not found");
        verify(clientRepository, never()).updateClient(initialClient); // to verify if the mock is never executed/used
    }
}