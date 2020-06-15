package com.myApp.springCRUD.model;

public class Address {
    private String line1;
    private String line2;
    private String line3;
    private int pinCode;

    public String getLine1() {
        return line1;
    }

    public Address setLine1(String line1) {
        this.line1 = line1;
        return this;
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
}
