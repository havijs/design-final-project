package com.knavid.entity;

import java.util.Date;

import com.knavid.Emailable;
import com.knavid.Phonable;

public class Customer implements Phonable, Emailable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private Date registrationDate;
    private String phoneNumber;
    private static int nextId = 1000;

    public Customer(int id, String firstName, String lastName, String email, int age, Date registrationDate, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
    }
    public Customer(String firstName, String lastName, String email, int age, Date registrationDate, String phoneNumber) {
        this.id = nextId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;

        nextId++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void print(){
        System.out.println("Id : " + this.getId());
        System.out.println("First Name : " + this.getFirstName());
        System.out.println("Last Name : " + this.getLastName());
        System.out.println("Email : " + this.getEmail());
        System.out.println("Age : " + this.getAge());
        System.out.println("Registration Date : " + this.getRegistrationDate());
        System.out.println("Phone Number : " + this.getPhoneNumber());


    }
}
