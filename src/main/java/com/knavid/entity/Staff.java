package com.knavid.entity;

public class Staff {
    private int id;
    private String fullName;
    private String emailAddress;
    private String role;
    private String skype;
    private String mobileNumber;

    public Staff(int id, String fullName, String emailAddress, String role, String skype, String mobileNumber) {
        this.id = id;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.role = role;
        this.skype = skype;
        this.mobileNumber = mobileNumber;
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

}
