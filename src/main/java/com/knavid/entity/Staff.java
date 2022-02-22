package com.knavid.entity;

import com.knavid.Emailable;
import com.knavid.Phonable;

public class Staff implements Phonable, Emailable {
    private int id;
    private String fullName;
    private String emailAddress;
    private String role;
    private String skype;
    private String mobileNumber;

    private static int nextId = 1000;

    public Staff(int id, String fullName, String emailAddress, String role, String skype, String mobileNumber) {
        this.id = id;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.role = role;
        this.skype = skype;
        this.mobileNumber = mobileNumber;
    }

    public Staff(String fullName, String emailAddress, String role, String skype, String mobileNumber) {
        this.id = nextId;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.role = role;
        this.skype = skype;
        this.mobileNumber = mobileNumber;

        nextId++;
    }


    public int getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSkype() {
        return this.skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String getPhoneNumber() {
        return this.mobileNumber;
    }

    @Override
    public String getEmail() {
        return this.emailAddress;
    }

}
