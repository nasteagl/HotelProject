package org.example.repositories;

import org.example.enums.RoomTypeEnum;
import org.example.models.RoomType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource(locations="classpath:application-test.properties")
@ComponentScan(basePackages = "org.example")
class RoomTypeRepositoryTest {
    @Autowired
    private Flyway flyway;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @BeforeEach
    void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void findAll_roomType_test() {
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        assertNotNull(roomTypes);
    }

    @Test
    void findById_roomType_test() {
        RoomType roomType = roomTypeRepository.findById(1);
        assertNotNull(roomType);
    }

   @Test
    void findById_roomType_notFound_test() {
        RoomType roomType = roomTypeRepository.findById(999);
        assertNull(roomType);

   }

    @Test
    void save_roomType_test() {
        RoomType roomType = RoomType.builder()
                .idRoomsType(1)
                .roomTypeEnum(RoomTypeEnum.FamilyRoom)
                .rooms(null)
                .build();

       RoomType savedRoomType = roomTypeRepository.save(roomType);
       assertNotNull(savedRoomType);
    }

    @Test
    void findAll_roomTypes_test() {
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        assertNotNull(roomTypes);
    }

    @Test
    void delete_roomType_test() {
        RoomType roomType = roomTypeRepository.findById(1);
        roomTypeRepository.deleteByIdRoomType(roomType.getIdRoomsType());
        assertNotNull(roomType);
    }

   @Test
    void patch_roomType_test() {
       RoomType roomType = RoomType.builder()
               .idRoomsType(1)
               .roomTypeEnum(RoomTypeEnum.FamilyRoom)
               .build();
        roomTypeRepository.UpdateRoomType(roomType);
        assertNotNull(roomType);
   }

   @Test
    void delete_roomType_notFound_test() {
        RoomType roomType = roomTypeRepository.findById(1);
        roomTypeRepository.deleteByIdRoomType(roomType.getIdRoomsType());
        assertNotNull(roomType);
   }

   @Test
    void deleteAll_roomTypes_test() {
        roomTypeRepository.findAll().forEach(roomType -> {
            roomTypeRepository.deleteRoomType(roomType);
        });


   }
}