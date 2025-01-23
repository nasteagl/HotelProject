package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Client;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;
    @Mock
    private EntityManager entityManager;
//    @Mock
//    private Flyway flyway;
//
//    @BeforeEach
//    void setUp() {
//        flyway.migrate();
//    }
//
//    @AfterEach
//    void tearDown() {
//        flyway.clean();
//    }

//    @AfterEach
//    void tearDown() {
//        entityManager.createQuery("delete from Client").executeUpdate();
//    }

    @Test
    void saveClient() {}

//    @Test
//    void testFindByIdClient() {
//        // given
//        Client client = Client.builder()
//                .firstname("mmm")
//                .lastname("nnnn")
//                .age(78)
//                .nrPersons(12)
//                .checkIn(new Date())
//                .checkOut(new Date())
//                .phoneNumber("123456")
//                .email("gfdsghvcdsdfgvcsdfg")
//                .build();
//
//        // when
//        when(entityManager.find(Client.class, 1)).thenReturn(client);
//        Client foundClient = clientRepository.findByIdClient(1);
//        // then
//        assertThat(foundClient).isEqualTo(client);
//    }
}