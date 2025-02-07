package org.example.repositories;

import org.example.models.Room;
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
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource(locations="classpath:application-test.properties")
@ComponentScan(basePackages = "org.example")
class RoomRepositoryTest {

    @Autowired
    private Flyway flyway;


    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    void setUp()  {
        flyway.clean();
        flyway.migrate();
    }
    @Test
    void saveRoomTesting() {
        Room room= Room.builder().floor(2).number(2).price(200).beds(2).reserved(false).roomType(roomTypeRepository.findById(2)).hotel(hotelRepository.findByIdHotel(1)).build();
        roomRepository.save(room);
        assertThat(room).isNotNull();
    }

    @Test
    void findById() {
        Room room_one=roomRepository.findById(1);
        assertThat(room_one).isNotNull();

    }

    @Test
    void findAll() {
        List<Room> rooms=roomRepository.findAll();
        assertThat(rooms).isNotNull();
        assertThat(rooms).hasSize( 1);

    }

    @Test
    void update() {
        Room room_update=Room.builder()
                .id_room(1)
                .floor(3)
                .number(3)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomTypeRepository.findById(5))
                .hotel(hotelRepository.findByIdHotel(2))
                .build();
        roomRepository.update(room_update);
        Room updated=roomRepository.findById(room_update.getId_room());

        assertThat(updated).isNotNull();
        assertThat(updated.getFloor()).isEqualTo(room_update.getFloor());
        assertThat(updated.getNumber()).isEqualTo(room_update.getNumber());
        assertThat(updated.getPrice()).isEqualTo(room_update.getPrice());
        assertAll("Grup de testari:",
                () -> assertEquals(300,updated.getPrice(),"Pretul s-a modificat cu succes"),
                () -> assertEquals(2,updated.getBeds(),"NR.beds s-au modificat cu succes")

        );

    }

    @Test
    void delete() {
        Room room_delete=Room.builder()
                .id_room(1)
                .floor(3)
                .number(3)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomTypeRepository.findById(5))
                .hotel(hotelRepository.findByIdHotel(2))
                .build();

        roomRepository.save(room_delete);
        //save room
        assertNotNull(roomRepository.findById(room_delete.getId_room()));
        //when
        roomRepository.deleteById(room_delete.getId_room());
        //then
        assertNull(roomRepository.findById(room_delete.getId_room()));
        assertEquals(1, roomRepository.findAll().size(),"Should be 1 room");

    }

    @Test
    void deleteById() {
        Room roomDeleteById=Room.builder()
                .id_room(1)
                .floor(3)
                .number(3)
                .price(300)
                .beds(2)
                .reserved(false)
                .roomType(roomTypeRepository.findById(5))
                .hotel(hotelRepository.findByIdHotel(2))
                .build();
        roomRepository.save(roomDeleteById);
        assertNotNull(roomRepository.findById(roomDeleteById.getId_room()));
        roomRepository.deleteById(roomDeleteById.getId_room());
        assertNull(roomRepository.findById(roomDeleteById.getId_room()));
        assertEquals(1, roomRepository.findAll().size(),"Should be 1 room");

    }

}