package org.example;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clientId")
    private int clientId;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @Column(name = "secondname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "age")
    private int age;

    @Column(name = "persons")
    private int persons;

    @Column(name = "adult_persons")
    private int adult_persons;

    @Column(name = "children_persons")
    private int children_persons;

    @Column(name = "checkin", nullable = false)
    private Date checkin;

    @Column(name = "checkout", nullable = false)
    private Date checkout;

    @Column(name = "phone_number", length = 50)
    private String phone_number;

    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    public Client(ClientBuilder builder) {
        this.clientId = builder.clientId;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.age = builder.age;
        this.persons = builder.persons;
        this.checkin = builder.checkin;
        this.checkout = builder.checkout;
        this.email = builder.email;
        this.phone_number = builder.phone_number;
    }

    public static class ClientBuilder {
        private int clientId;
        private String firstname;
        private String lastname;
        private int age;
        private int persons;
        private Date checkin;
        private Date checkout;
        private String email;
        private String phone_number;


        public ClientBuilder setClientId(int clientId) {
            this.clientId = clientId;
            return this;
        }

        public ClientBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public ClientBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public ClientBuilder setAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            this.age = age;
            return this;
        }

        public ClientBuilder setNrPers(int persons) {
            if (persons < 0) {
                throw new IllegalArgumentException("Persons cannot be negative");
            }
            this.persons = persons;
            return this;
        }

        public ClientBuilder setCheckin(Date checkin) {
            this.checkin = checkin;
            return this;
        }

        public ClientBuilder setCheckout(Date checkout) {
            this.checkout = checkout;
            return this;
        }

        public ClientBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder setPhoneNumber(String phone_number) {
            this.phone_number = phone_number;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }


}
