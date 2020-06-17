package com.myApp.springCRUD.datasource;

import com.myApp.springCRUD.model.Address;
import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.repository.AddressRepository;
import com.myApp.springCRUD.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;


@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTesting {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void findStudent() {
        Optional<Student> student = studentRepository.findById(55);
        assertNotNull(student);
        if (student.isPresent()) {
            System.out.println("\nTest 1:");
            System.out.println(student.get().toString());
        } else
            System.out.println("Not Found");
    }

    @Test
    public void findStudentByRollNo() {
        Optional<Student> student = studentRepository.findByRollNo(117);
        assertNotNull(student);
        if (student.isPresent()) {
            System.out.println("\nTest 2:");
            System.out.println(student.get().toString());
        } else
            System.out.println("Not Found");
    }

    @Test
    public void findAddressByStudentRollNo() {
        List<Address> allAddress = addressRepository.findByStudentId(63);
        if (allAddress.size() > 0) {
            System.out.println("\nTest 2:");
            for (Address address : allAddress) {
                System.out.println(address.toString());
            }
            System.out.println("\n");
        } else {
            System.out.println("Not Found");
        }
    }
}
