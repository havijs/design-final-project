package com.knavid.entity;

import java.util.Date;

public class Appointment {
    private int id;
    private Date date;
    private Employee employee;
    private Customer customer;

    private static int nextId = 1000;

    public Appointment(Date date, Employee employee, Customer customer) {
        this.id = nextId;
        this.date = date;
        this.employee = employee;
        this.customer = customer;

        nextId++;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void print() {
        System.out.println("Id : " + this.getId());
        System.out.println("Date : " + this.getDate());
        System.out.println("Employee : " + this.getEmployee().getId());
        System.out.println("Customer : " + this.getCustomer().getId());



    }
}
