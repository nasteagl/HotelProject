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
}
