package org.example.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.lang.*;

@Entity
@Table(name="room", schema = "hotel_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrementareee
    @Column(name="room_id")
    private Integer id_room;

    @Column(name="room_floor", nullable = false)
    private Integer floor;

    @Column(name="room_number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "room_price", nullable = false)
    private Integer price;

    @Column(name="room_beds", nullable = false)
    private Integer beds;

    @Column(name="room_reserved", nullable = false)
    private Boolean reserved;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="rooms_type_id")
    private RoomType roomType;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

}
