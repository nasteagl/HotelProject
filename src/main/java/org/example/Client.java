package org.example;
import java.util.Date;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Client {
    private int clientId;
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


    private Client(ClientBuilder builder) {
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
            this.age = age;
            return this;
        }

        public ClientBuilder setNrPers(int persons) {
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
