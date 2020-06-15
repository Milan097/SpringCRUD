package com.myApp.springCRUD.controller;

import com.myApp.springCRUD.dao.StudentDAO;
import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.model.StudentAddress;
import com.myApp.springCRUD.repository.AddressRepository;
import com.myApp.springCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /*save student to db*/
    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@Validated @RequestBody StudentAddress studentAddress) {
        Student student = new Student(studentAddress.getName(), studentAddress.getRollNo());
        return stuDAO.createStudent(student, studentAddress.getAddress());
    }

    /*get an student from db*/
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") Integer stId) {
        Optional<Student> st = studentRepository.findById(stId);
        if (!st.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(st.get());
        }
    }

    /*update Student*/
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer stId, @Validated @RequestBody Student newSt) {
        Student st = stuDAO.updateOne(stId, newSt);
        if (st == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(st);
        }
    }


    /*Delete Student*/
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable(value = "id") Integer stId) {
        Optional<Student> st = studentRepository.findById(stId);
        if (!st.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            studentRepository.delete(st.get());
            return ResponseEntity.ok().build();
        }
    }
}	
