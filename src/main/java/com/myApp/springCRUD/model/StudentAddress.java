package com.myApp.springCRUD.model;

public class StudentAddress {
    StudentAddress() {
    }

    private int id;
    private String name;
    private int rollNo;
    private Address address;

    public StudentAddress(String name, int rollNo, Address address) {
        this.name = name;
        this.rollNo = rollNo;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
