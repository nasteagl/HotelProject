package org.example;
import java.util.Date;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Client {
    private String firstname;
    private String lastname;
    private int age;
    private int persons;
    private int adult_persons;
    private int children_persons;
    private Date checkin;
    private Date checkout;
    private String phone_number;
    private String email;


}
