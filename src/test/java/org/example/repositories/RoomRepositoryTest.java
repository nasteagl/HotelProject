package org.example.repositories;

import jakarta.persistence.EntityNotFoundException;
import org.example.models.Hotel;
import org.example.models.Room;
import org.example.models.RoomType;
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
        RoomType roomType = roomTypeRepository.findById(2);
        Hotel hotel = hotelRepository.findByIdHotel(1);

        assertNotNull(roomType, "RoomType with ID 2 should exist");
        assertNotNull(hotel, "Hotel with ID 1 should exist");

        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(200)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .hotel(hotel)
                .build();
        roomRepository.save(room);
        assertThat(room).isNotNull();
    }


    @Test
    void findById() {
        Room room_one=roomRepository.findById(1);
        assertNotNull(room_one,"room not found");

    }

    @Test
    void findAll() {
        List<Room> rooms=roomRepository.findAll();
        assertNotNull(rooms,"List in null ");
        assertThat(rooms).hasSize( 1);


    }

    @Test
    void update() {
        Room room_update = Room.builder()
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
        Room updated = roomRepository.findById(room_update.getId_room());

        assertThat(updated).isNotNull();
        assertThat(updated.getFloor()).isEqualTo(room_update.getFloor());
        assertThat(updated.getNumber()).isEqualTo(room_update.getNumber());
        assertThat(updated.getPrice()).isEqualTo(room_update.getPrice());

        // Verifică dacă toate proprietățile sunt actualizate corect
        assertAll("Grup de testari:",
                () -> assertEquals(300, updated.getPrice(), "Pretul s-a modificat cu succes"),
                () -> assertEquals(2, updated.getBeds(), "Nr. de paturi s-au modificat cu succes"));
    }


    @Test
    void delete() {
        Room room_delete = Room.builder()
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
        assertNotNull(roomRepository.findById(room_delete.getId_room()));
        roomRepository.deleteById(room_delete.getId_room());

        // Verifică că obiectul a fost șters
        assertNull(roomRepository.findById(room_delete.getId_room()));
        assertEquals(1, roomRepository.findAll().size(), "Ar trebui să existe doar 1 cameră");
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

    @Test
    void deleteRoomTest() {
        // Creează și salvează o cameră
        RoomType roomType = roomTypeRepository.findById(2);
        Hotel hotel = hotelRepository.findByIdHotel(1);

        assertNotNull(roomType, "RoomType with ID 2 should exist");
        assertNotNull(hotel, "Hotel with ID 1 should exist");

        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(200)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .hotel(hotel)
                .build();

        roomRepository.save(room);
        Room savedRoom = roomRepository.findById(room.getId_room());
        assertNotNull(savedRoom, "Room should be saved");
        roomRepository.delete(savedRoom);
        Room deletedRoom = roomRepository.findById(room.getId_room());
        assertNull(deletedRoom, "Room should be deleted");
        List<Room> allRooms = roomRepository.findAll();
        assertEquals(1, allRooms.size(), "There should be no rooms in the repository");
    }
    @Test
    void deleteRoomUpdatesListTest() {
        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(200)
                .beds(2)
                .reserved(false)
                .roomType(roomTypeRepository.findById(2))
                .hotel(hotelRepository.findByIdHotel(1))
                .build();
        roomRepository.save(room);

        Room savedRoom = roomRepository.findById(room.getId_room());
        assertNotNull(savedRoom);
        roomRepository.delete(savedRoom);
        List<Room> allRooms = roomRepository.findAll();
        assertEquals(1, allRooms.size(), "There should be no rooms in the repository");    }

    @Test
    void deleteRoomCascadeTest() {
        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(200)
                .beds(2)
                .reserved(false)
                .roomType(roomTypeRepository.findById(2))
                .hotel(hotelRepository.findByIdHotel(1)) // Verifică impactul asupra hotelului
                .build();
        roomRepository.save(room);

        assertNotNull(roomRepository.findById(room.getId_room()));
        assertNotNull(hotelRepository.findByIdHotel(1));
        roomRepository.delete(room);
        assertNotNull(hotelRepository.findByIdHotel(1));
    }
    @Test
    void deleteNonExistingRoom() {
        assertThrows(IllegalArgumentException.class, () -> {
            roomRepository.deleteById(999); // ID care nu există
        });

    }
    @Test
    void saveRoomWithInvalidPrice() {
        RoomType roomType = roomTypeRepository.findById(2);
        Hotel hotel = hotelRepository.findByIdHotel(1);

        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(-200)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .hotel(hotel)
                .build();

        assertThrows(IllegalArgumentException.class, () -> roomRepository.save(room), "Price must be greater than zero");
    }
    @Test
    void saveRoomWithInvalidBeds() {
        RoomType roomType = roomTypeRepository.findById(2);
        Hotel hotel = hotelRepository.findByIdHotel(1);

        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(200)
                .beds(-1)
                .reserved(false)
                .roomType(roomType)
                .hotel(hotel)
                .build();

        assertThrows(IllegalArgumentException.class, () -> roomRepository.save(room));
    }
    @Test
    void TestRoomWithInvalidFloor(){
        RoomType roomType = roomTypeRepository.findById(2);
        Hotel hotel = hotelRepository.findByIdHotel(1);
        Room room = Room.builder()
                .floor(-2)
                .number(2)
                .price(200)
                .beds(1)
                .reserved(false)
                .roomType(roomType)
                .hotel(hotel)
                .build();
        assertThrows(IllegalArgumentException.class, () -> roomRepository.save(room));
    }
    @Test
    void TestRoomWithNullReserved(){
        RoomType roomType = roomTypeRepository.findById(2);
        Hotel hotel = hotelRepository.findByIdHotel(1);
        Room room = Room.builder()
                .floor(-2)
                .number(2)
                .price(200)
                .beds(1)
                .reserved(null)
                .roomType(roomType)
                .hotel(hotel)
                .build();
        assertThrows(IllegalArgumentException.class, () -> roomRepository.save(room));
    }

   @Test
    void TestRoomWithWrongNumber(){
       RoomType roomType = roomTypeRepository.findById(2);
       Hotel hotel = hotelRepository.findByIdHotel(1);
       Room room = Room.builder()
               .floor(2)
               .number(-2)
               .price(200)
               .beds(1)
               .reserved(null)
               .roomType(roomType)
               .hotel(hotel)
               .build();
       assertThrows(IllegalArgumentException.class, () -> roomRepository.save(room));
   }
    @Test
    void saveRoomWithNullRoomType() {
        Hotel hotel = hotelRepository.findByIdHotel(1);

        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(200)
                .beds(2)
                .reserved(false)
                .roomType(null)
                .hotel(hotel)
                .build();

        assertThrows(IllegalArgumentException.class, () -> roomRepository.save(room));
    }
    @Test
    void saveRoomWithNullHotel() {
        RoomType roomType = roomTypeRepository.findById(2);

        Room room = Room.builder()
                .floor(2)
                .number(2)
                .price(200)
                .beds(2)
                .reserved(false)
                .roomType(roomType)
                .hotel(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> roomRepository.save(room));
    }
}