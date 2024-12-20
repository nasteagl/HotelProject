package org.example;
import java.util.Date;

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

    public Client(String firstname, String lastname, int age, int persons, int adult_persons, int children_persons, Date checkin, Date checkout, String phone_number, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.persons = persons;
        this.adult_persons = adult_persons;
        this.children_persons = children_persons;
        this.checkin = checkin;
        this.checkout = checkout;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getAdult_persons() {
        return adult_persons;
    }

    public void setAdult_persons(int adult_persons) {
        this.adult_persons = adult_persons;
    }

    public int getChildren_persons() {
        return children_persons;
    }

    public void setChildren_persons(int children_persons) {
        this.children_persons = children_persons;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
