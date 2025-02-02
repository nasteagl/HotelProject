package org.example.repositories;

import org.example.models.Client;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
class ClientRepositoryTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HotelRepository hotelRepository;

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
                .checkIn(new Date())
                .checkOut(new Date())
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotelRepository.findByIdHotel(1))
                .build();

        // when
        clientRepository.saveClient(client);
        Client savedClient = clientRepository.findByIdClient(client.getClient_id());

        // then
        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getClient_id()).isGreaterThan(0);
    }

    @Test
    void findClientByIdTest() {

        // when
        Client actualClient = clientRepository.findByIdClient(1);

        // then
        assertThat(actualClient).isNotNull();
    }

    @Test
    void findAllClientsTest() {

        // when
        List<Client> actualClientList = clientRepository.findAllClients();

        // then
        assertThat(actualClientList).isNotNull();
        assertThat(actualClientList.size()).isEqualTo(3);
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
                .checkIn(new Date())
                .checkOut(new Date())
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
//                () -> assertEquals(new Date(), updatedClient.getCheckIn(), "CheckIn updated"),
//                () -> assertEquals(new Date(), updatedClient.getCheckOut(), "CheckOut updated"),
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
                .checkIn(new Date())
                .checkOut(new Date())
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
        assertEquals(3, clientRepository.findAllClients().size(), "There should be 3 Clients");
    }

    @Test
    void deleteClientByIdTest() {

        // given
        Client client = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .age(24)
                .nrPersons(5)
                .checkIn(new Date())
                .checkOut(new Date())
                .phoneNumber("56327495")
                .email("john@doe.com")
                .hotel(hotelRepository.findByIdHotel(1))
                .build();

        clientRepository.saveClient(client);

        // Client saved
        assertNotNull(clientRepository.findByIdClient(client.getClient_id()));

        // when
        clientRepository.deleteById(client.getClient_id());

        // then
        assertNull(clientRepository.findByIdClient(client.getClient_id()));
        assertEquals(3, clientRepository.findAllClients().size(), "There should be 3 Clients");
    }
}