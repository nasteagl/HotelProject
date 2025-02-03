package org.example.repositories;

import org.example.models.Hotel;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackages = "org.example")
class HotelRepositoryTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void saveHotelTest() {
        //given
        Hotel hotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        //when
        hotelRepository.saveHotel(hotel);
        Hotel savedHotel = hotelRepository.findByIdHotel(hotel.getHotel_id());

        //then
        assertThat(savedHotel).isNotNull();
        assertThat(savedHotel.getHotel_id()).isGreaterThan(0);

    }

    @Test
    void findByIdHotelTest() {
        //when
        Hotel actualHotel = hotelRepository.findByIdHotel(1);

        //then
        assertThat(actualHotel).isNotNull();
    }

    @Test
    void findAllHotelsTest() {
        //when
        List<Hotel> actualHotelList = hotelRepository.findAllHotels();

        //then
        assertThat(actualHotelList).isNotNull();
        assertThat(actualHotelList).isNotEmpty();
    }

    @Test
    void updateHotelTest() {
        //given
        Hotel hotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        //when
        hotelRepository.updateHotel(hotel);
        Hotel updatedHotel = hotelRepository.findByIdHotel(hotel.getHotel_id());

        //then
        assertAll(
                () ->assertNotNull(updatedHotel),
                () -> assertEquals(1,updatedHotel.getHotel_id(), "Incorrect hotel ID"),
                () -> assertEquals("Country",updatedHotel.getHotelCountry(), "incorrect name"),
                () -> assertEquals("City",updatedHotel.getHotelCity(), "incorrect CITY"),
                () -> assertEquals("Address",updatedHotel.getHotelAddress(), "incorrect address"),
                () -> assertEquals("email@example.com",updatedHotel.getHotelEmail(), "incorrect email"),
                () -> assertEquals("060000000",updatedHotel.getHotelPhone(), "incorrect phone")
        );
    }

    @Test
    void deleteHotelTest() {
        //given
        Hotel hotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        hotelRepository.saveHotel(hotel);

        //hotel saved
        assertNotNull(hotelRepository.findByIdHotel(hotel.getHotel_id()));

        //when
        hotelRepository.deleteHotel(hotel);

        //then
        assertNull(hotelRepository.findByIdHotel(hotel.getHotel_id()));

    }

    @Test
    void deleteByIdTest() {
        //given
        Hotel hotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        hotelRepository.saveHotel(hotel);

        //hotel saved
        assertNotNull(hotelRepository.findByIdHotel(hotel.getHotel_id()));

        //when
        hotelRepository.deleteById(hotel.getHotel_id());

        //then
        assertNull(hotelRepository.findByIdHotel(hotel.getHotel_id()));
    }
}