package com.myApp.springCRUD.model;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;


@SuppressWarnings("unused")
@Entity
@Table(name = "Students")
@EntityListeners(AuditingEntityListener.class)
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "roll_no")
    private int rollNo;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(referencedColumnName = "id" , name = "address_id")
//    private Address address;

    public Student() {
    }

    public Student(@NonNull String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
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

}
