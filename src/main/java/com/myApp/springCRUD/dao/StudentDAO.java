package com.myApp.springCRUD.dao;

import java.util.List;
import java.util.Optional;

import com.myApp.springCRUD.model.Address;
import com.myApp.springCRUD.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.repository.StudentRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SuppressWarnings("unused")
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
		// error handling
		return ResponseEntity.ok(createdStudent);
	}

	// Add Student's Address by id
	public ResponseEntity<Address> addAddressById(Integer stId, Address address) {
		Optional<Student> st = stuRepository.findById(stId);
		if(!st.isPresent()) {
			System.out.println("Student With Given Id Not exist");
			return ResponseEntity.notFound().build();
		} else {
			address.setStudentId(stId);
			Address addedAddress = addressRepository.save(address);
			// error handling
			return ResponseEntity.ok(addedAddress);
		}
	}

	// Add Student's Address by roll no
	public ResponseEntity<Address> addAddressByRollNo(int studentRollNo,  Address address) {
		Optional<Student> st = stuRepository.findByRollNo(studentRollNo);
		if(!st.isPresent()) {
			System.out.println("Student With Given RollNo Not exist");
			return ResponseEntity.notFound().build();
		} else {
			address.setStudentId(st.get().getId());
			Address addedAddress = addressRepository.save(address);
			// error handling
			return ResponseEntity.ok(addedAddress);
		}
	}

	// get Student's Address by roll_no
	public ResponseEntity<List<Address>> getAddressByStudentRollNo(int studentRollNo) {
		Optional<Student> st = stuRepository.findByRollNo(studentRollNo);
		if(st.isPresent()) {
			List<Address> allAddresses = addressRepository.findByStudentId(st.get().getId());
			return ResponseEntity.ok(allAddresses);
		} else {
			System.out.println("Student With Given RollNo Not exist");
			return ResponseEntity.notFound().build();
		}
	}


	// update student by id
	public Student updateOne(Integer stId, Student newStudent) {
		Optional<Student> st = stuRepository.findById(stId);
		if (!st.isPresent()) {
			return null;
		} else {
			Student student = st.get();
			student.setName(newStudent.getName());
			student.setRollNo(newStudent.getRollNo());

			return stuRepository.save(student);
			// error handling
		}
	}


	// Delete Student with all their Addresses
	public ResponseEntity<Object> deleteStudentWithAddresses(int studentId) {
		Optional<Student> student = stuRepository.findById(studentId);
		if(student.isPresent()) {
			List<Address> allAddresses = addressRepository.findByStudentId(student.get().getId());
			for ( Address address : allAddresses) {
				addressRepository.delete(address);
			}
			stuRepository.delete(student.get());
			return ResponseEntity.ok().build();
		} else {
			System.out.println("No Student with given Id");
			return ResponseEntity.notFound().build();
		}
	}

}
