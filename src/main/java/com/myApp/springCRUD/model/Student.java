package com.myApp.springCRUD.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;


@Entity
@Table(name = "Students")
@EntityListeners(AuditingEntityListener.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private int rollNo;

    @NonNull
    private Address address;

    public Student(@NonNull String name, int rollNo, @NonNull Address address) {
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

    @NonNull
    public Address getAddress() {
        return address;
    }

    public void setAddress(@NonNull Address address) {
        this.address = address;
    }
}
