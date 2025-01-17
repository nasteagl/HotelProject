package org.example.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.lang.*;

import java.util.Date;

@Entity
@Table(name="client", schema = "hotel_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer client_id;

    @Column(name = "client_first_name", length = 50, nullable = false)
    private String firstname;

    @Column(name = "client_last_name", length = 50, nullable = false)
    private String lastname;

    @Column(name = "client_age", nullable = false)
    private Integer age;

    @Column(name = "client_nr_pers", nullable = false)
    private Integer nr_pers;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @Column(name = "client_check_in", nullable = false)
    private Date checkin;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @Column(name = "client_check_out", nullable = false)
    private Date checkout;

    @Column(name = "client_phone_number", length = 50, nullable = false)
    private String phone_number;

    @Column(name = "client_email", length = 50, nullable = false)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="hotel_id")
    private Hotel hotel;
}
