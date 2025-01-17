package org.example.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.enums.RoomTypeEnum;
import java.lang.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms_type", schema = "hotel_schema") //num tab in baza de date
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rooms_type_id")
    private Integer idRoomsType;

    @Enumerated(EnumType.STRING) //  enum-ul va fi salvat ca string
    private RoomTypeEnum roomTypeEnum;

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "roomType", orphanRemoval = false)
//    private List<Room> rooms;
}