package org.example.repositories;

import org.example.models.Client;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackages = "org.example")
class ClientRepositoryTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HotelRepository hotelRepository;

    static Date date;

    @BeforeAll
    static void setup() {
        date = new Date();
    }

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void saveClientTest() {

        // given
        Client client = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .age(24)
                .nrPersons(5)
                .checkIn(date)
                .checkOut(date)
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotelRepository.findByIdHotel(1))
                .build();

        // when
        clientRepository.saveClient(client);
        Client savedClient = clientRepository.findByIdClient(client.getClient_id());

        // then
        assertNotNull(savedClient.getClient_id());
    }

    @Test
    void findClientByIdTest() {

        // when
        Client actualClient = clientRepository.findByIdClient(1);

        // then
        assertNotNull(actualClient, "client not found");
    }

    @Test
    void findAllClientsTest() {

        // when
        List<Client> actualClientList = clientRepository.findAllClients();

        // then
        assertNotNull(actualClientList, "List of Clients is null");
    }

    @Test
    void updateClientTest() {

        // given
        Client client = Client.builder()
                .client_id(1)
                .firstname("John")
                .lastname("Doe")
                .age(24)
                .nrPersons(5)
                .checkIn(date)
                .checkOut(date)
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotelRepository.findByIdHotel(1))
                .build();

        // when
        clientRepository.updateClient(client);
        Client updatedClient = clientRepository.findByIdClient(client.getClient_id());

        // then (expected -- actual)
        assertAll(
                () -> assertNotNull(updatedClient),
                () -> assertEquals(1, updatedClient.getClient_id(), "Client id is incorrect"),
                () -> assertEquals("John", updatedClient.getFirstname(), "Firstname is incorrect"),
                () -> assertEquals("Doe", updatedClient.getLastname(), "Lastname is incorrect"),
                () -> assertEquals(24, updatedClient.getAge(), "Age is incorrect"),
                () -> assertEquals(5, updatedClient.getNrPersons(), "Persons is incorrect"),
                () -> assertEquals(date, updatedClient.getCheckIn(), "CheckIn updated"),
                () -> assertEquals(date, updatedClient.getCheckOut(), "CheckOut updated"),
                () -> assertEquals("56327495", updatedClient.getPhoneNumber(), "PhoneNumber is incorrect"),
                () -> assertEquals("john@doe.com", updatedClient.getEmail(), "Email is incorrect"),
                () -> assertEquals(hotelRepository.findByIdHotel(1), updatedClient.getHotel(), "Hotel is incorrect")
        );
    }

    @Test
    void deleteClientTest() {

        // given
        Client client = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .age(24)
                .nrPersons(5)
                .checkIn(date)
                .checkOut(date)
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotelRepository.findByIdHotel(1))
                .build();

        clientRepository.saveClient(client);

        // Client saved
        assertNotNull(clientRepository.findByIdClient(client.getClient_id()));

        // when
        clientRepository.deleteClient(client);

        // then
        assertNull(clientRepository.findByIdClient(client.getClient_id()));
    }

    @Test
    void deleteClientByIdTest() {

        // given
        Client client = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .age(24)
                .nrPersons(5)
                .checkIn(date)
                .checkOut(date)
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotelRepository.findByIdHotel(1))
                .build();

        clientRepository.saveClient(client);

        // Client saved
        assertNotNull(clientRepository.findByIdClient(client.getClient_id()));

        // when
        clientRepository.deleteClientById(client.getClient_id());

        // then
        assertNull(clientRepository.findByIdClient(client.getClient_id()));
    }
}