package org.example.dto;

import lombok.Builder;
import lombok.Value;
import org.example.models.Client;

import java.util.Date;

@Value
@Builder
public class ClientDto {
    Integer client_id;
    String firstname;
    String lastname;
    Integer age;
    Integer nrPersons;
    Date checkIn;
    Date checkOut;
    String phoneNumber;
    String email;
    HotelDto hotel;

    // Entity -> Dto
    static public ClientDto fromClient(Client client) {
        return ClientDto.builder()
                .client_id(client.getClient_id())
                .firstname(client.getFirstname())
                .lastname(client.getLastname())
                .age(client.getAge())
                .nrPersons(client.getNrPersons())
                .checkIn(client.getCheckIn())
                .checkOut(client.getCheckOut())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .hotel(HotelDto.fromHotel(client.getHotel()))
                .build();
    }

    // Dto -> Entity
    static public Client fromClientDto(ClientDto clientDto) {
            return Client.builder()
                    .client_id(clientDto.getClient_id())
                    .firstname(clientDto.getFirstname())
                    .lastname(clientDto.getLastname())
                    .age(clientDto.getAge())
                    .nrPersons(clientDto.getNrPersons())
                    .checkIn(clientDto.getCheckIn())
                    .checkOut(clientDto.getCheckOut())
                    .phoneNumber(clientDto.getPhoneNumber())
                    .email(clientDto.getEmail())
                    .hotel(HotelDto.fromHotelDto(clientDto.getHotel()))
                    .build();
    }

    /*
        TODO: OOOOOOOOO: De creat package Test (H2 database in-memory, JUnit, Mockito) pentru toate trei layere (Client, Hotel, Room, RoomType ----) -> WAITING
        TODO: (TODO = hint) JUnit5, Mockito ultimul, stari de testare (actual/expected), pentru H2 si testing (anotari) (Client, Hotel, Room, RoomType ----) -> WAITING

        TODO: (punishment) In loc de JpaRepository sa utilizam EntityManager (Client, Hotel, Room, RoomType +++-) -> IN PROCESS (3/4 DONE)
        TODO: De terminat Busness logica pentru CRUD + patch (pentru fiecare camp) (Client, Hotel, Room, RoomType +-++) -> IN PROCESS (3/4 DONE)
        TODO: Toate layere sa foloseasca Dto (Client, Hotel, Room, RoomType +-++) -> IN PROCESS (3/4 DONE)

        TODO: metode statice Dto -> Entity (Client, Hotel, Room, RoomType ++++) -> DONE
        TODO: modelMapper o7 -> DONE
        TODO: de reparat logica -> DONE
    */
}
