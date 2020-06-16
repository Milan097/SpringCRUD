package com.myApp.springCRUD.controller;

import com.myApp.springCRUD.dao.StudentDAO;
import com.myApp.springCRUD.model.Address;
import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.model.StudentAddress;
import com.myApp.springCRUD.repository.AddressRepository;
import com.myApp.springCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
@RequestMapping("/school")
public class StudentController {

    @Autowired
    StudentDAO stuDAO;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    // add student to db
    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@Validated @RequestBody StudentAddress studentAddress) {
        Student student = new Student(studentAddress.getName(), studentAddress.getRollNo());
        return stuDAO.createStudent(student, studentAddress.getAddress());
    }

    // add address of student by student_id
    @PostMapping("/student/id/{id}/address")
    public ResponseEntity<Address> addAddressById(@PathVariable(value = "id") Integer stId, @Validated @RequestBody Address address) {
        try {
            return stuDAO.addAddressById(stId, address);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    // add address of student by roll_no
    @PostMapping("/student/rollNo/{rollNo}/address")
    public ResponseEntity<Address> addAddressByRollNo(@PathVariable(value = "rollNo") Integer stRollNo, @Validated @RequestBody Address address) {
        return stuDAO.addAddressByRollNo(stRollNo, address);
    }


    // get an student from db
    @GetMapping("/student/id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Integer stId) {
        Optional<Student> st = studentRepository.findById(stId);
        if (!st.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(st.get());
        }
    }

    // get a student by roll_no
    @GetMapping("/student/rollNo/{rollNo}")
    public ResponseEntity<Student> getStudentByRollNo(@PathVariable(value = "rollNo") Integer stRollNo) {
        Optional<Student> st = studentRepository.findByRollNo(stRollNo);
        if (!st.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(st.get());
        }
    }

    // get all address of a student by rollNo
    @GetMapping("/student/rollNo/{rollNo}/address")
    public ResponseEntity<List<Address>> getAddressByRollNo(@PathVariable(value = "rollNo") int stRollNo) {
        return stuDAO.getAddressByStudentRollNo(stRollNo);
    }


    // update Student
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer stId, @Validated @RequestBody Student newSt) {
        Student st = stuDAO.updateOne(stId, newSt);
        if (st == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(st);
        }
    }


    // Delete Student
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable(value = "id") Integer stId) {
        return stuDAO.deleteStudentWithAddresses(stId);
    }
}	
