package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private int clientId;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "age")
    private int age;

    @Column(name = "nr_pers")
    private int nr_pers;

    @Column(name = "checkin")
    private String checkin;

    @Column(name = "checkout")
    private String checkout;

    @Column(name = "phone_number", length = 50)
    private String phone_number;

    @Column(name = "email", length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    public Client(ClientBuilder builder) {
        this.clientId = builder.clientId;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.age = builder.age;
        this.nr_pers = builder.nr_pers;
        this.checkin = builder.checkin;
        this.checkout = builder.checkout;
        this.email = builder.email;
        this.phone_number = builder.phone_number;
        this.hotel = builder.hotel;
    }

    public static class ClientBuilder {
        private int clientId;
        private String firstname;
        private String lastname;
        private int age;
        private int nr_pers;
        private String checkin;
        private String checkout;
        private String email;
        private String phone_number;
        public Hotel hotel;


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

        public ClientBuilder setNrPers(int nr_pers) {
            if (nr_pers < 0) {
                throw new IllegalArgumentException("Persons cannot be negative");
            }
            this.nr_pers = nr_pers;
            return this;
        }

        public ClientBuilder setCheckin(String checkin) {
            this.checkin = checkin;
            return this;
        }

        public ClientBuilder setCheckout(String checkout) {
            this.checkout = checkout;
            return this;
        }

        public ClientBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder setHotel(String phone_number) {
            this.phone_number = phone_number;
            return this;
        }

        public ClientBuilder setHotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }


        public Client build() {
            return new Client(this);
        }
    }


}
