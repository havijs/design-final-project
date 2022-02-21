package com.knavid.entity;

import java.util.Date;

public class Appointment {
    private int id;
    private Date date;
    private Staff staff;
    private Customer customer;

    public Appointment(int id, Date date, Staff staff, Customer customer) {
        this.id = id;
        this.date = date;
        this.staff = staff;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
