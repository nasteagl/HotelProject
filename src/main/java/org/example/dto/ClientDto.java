package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int client_id;
    private String firstname;
    private String lastname;
    private int age;
    private String phone_number;
    private String email;
    private HotelDto hotel;

    /*
    TODO: metode statice Dto -> Entity
    TODO: Press F for modelMapper o7
    TODO: Toate layere sa foloseasca Dto
    TODO: De terminat Busness logica pentru CRUD + patch (pentru fiecare camp)
    TODO: (punishment) In loc de JpaRepository sa utilizam EntityManager
    TODO: OOOOOOOOO: De creat package Test (H2 database in-memory, JUnit, Mockito) pentru toate trei layere
    TODO: (TODO = hint) JUnit5, Mockito ultimul, stari de testare (actual/expected), pentru H2 si testing (anotari)
    */
}
