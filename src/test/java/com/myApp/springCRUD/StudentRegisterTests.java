package com.myApp.springCRUD;

import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRegisterTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findStudent() {
        Optional<Student> student = studentRepository.findById(100);
        assertNotNull(student);
        if(student.isPresent())
            System.out.println(student.get());
        else
            System.out.println("Not Found");
    }
}
