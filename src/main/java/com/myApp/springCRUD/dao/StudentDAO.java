package com.myApp.springCRUD.dao;

import java.util.Optional;

import com.myApp.springCRUD.model.Address;
import com.myApp.springCRUD.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.repository.StudentRepository;

@Service
public class StudentDAO {

	@Autowired
	StudentRepository stuRepository;

	@Autowired
	AddressRepository addressRepository;


	// Add Student with address
	public ResponseEntity<Student> createStudent(Student student, Address address) {
		Student createdStudent = stuRepository.save(student);

		int sId = createdStudent.getId();
		address.setStudentId(sId);
		Address createdAddress = addressRepository.save(address);

		return ResponseEntity.ok(createdStudent);
	}

	// update student by id
	public Student updateOne(Integer stId, Student newStudent) {
		Optional<Student> st = stuRepository.findById(stId);
		if (st == null) {
			return null;
		} else {
			Student student = st.get();
			student.setName(newStudent.getName());
			student.setRollNo(newStudent.getRollNo());

			Student updatedStudent = stuRepository.save(student);
			return updatedStudent;
		}
	}

}
