package com.myApp.springCRUD.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.repository.StudentRepository;

@Service
public class StudentDAO {

	@Autowired
	StudentRepository stuRepository;
	
	/*save students*/
	public Student save(Student st) {
		return stuRepository.save(st);
	}
	
	/*get student by id*/
	public Student findOne(Integer stId) {
		Optional<Student> student = stuRepository.findById(stId);
		return (student == null) ? null : student.get();
	}
	
	/*delete students*/
	public void delete(Student st) {
		stuRepository.delete(st);
	}
}
