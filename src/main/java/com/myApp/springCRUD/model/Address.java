package com.myApp.springCRUD.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "Address")
@EntityListeners(AuditingEntityListener.class)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "line3")
    private String line3;

    @Column(name = "pincode")
    private int pinCode;

    public String getLine1() {
        return line1;
    }

    public Address setLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getLine2() {
        return line2;
    }

    public Address setLine2(String line2) {
        this.line2 = line2;
        return this;
    }

    public String getLine3() {
        return line3;
    }

    public Address setLine3(String line3) {
        this.line3 = line3;
        return this;
    }

    public int getPinCode() {
        return pinCode;
    }

    public Address setPinCode(int pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int addressId) {
        this.id = addressId;
    }

}
