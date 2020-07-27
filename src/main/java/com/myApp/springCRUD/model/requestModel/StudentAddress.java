package com.myApp.springCRUD.model.requestModel;

import com.myApp.springCRUD.model.Address;

import java.util.Set;

@SuppressWarnings("unused")
public class StudentAddress {
    StudentAddress() {
    }

    private int id;
    private String name;
    private int rollNo;
    private Address address;
    private String password;
    private Set<String> roles;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentAddress(String name, int rollNo, Address address, String password, Set<String> roles) {
        this.name = name;
        this.rollNo = rollNo;
        this.address = address;
        this.password = password;
        this.roles = roles;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
