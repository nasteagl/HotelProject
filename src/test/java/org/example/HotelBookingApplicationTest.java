package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = HotelBookingApplication.class)
class HotelBookingApplicationTest {

    @Test
    void main() {
        assertTrue(true);
    }

    Calculator calc = new Calculator();

    @Test
    void addNumbers() {
        //given
        int nOne = 10;
        int nTwo = 20;

        //when
        int result = calc.add(nOne, nTwo);

        // then
        int expectedResult = 30;
        assertThat(result).isEqualTo(expectedResult);
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
}