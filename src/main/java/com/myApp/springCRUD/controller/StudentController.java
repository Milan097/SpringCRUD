package com.myApp.springCRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myApp.springCRUD.dao.StudentDAO;
import com.myApp.springCRUD.model.Student;

@Service
@RequestMapping("/school")
public class StudentController {
	
	@Autowired
	StudentDAO stuDAO;
	
	/*save student to db*/
	@PostMapping("/student")
	public Student createStudent(@Validated @RequestBody Student st)  {
		return stuDAO.save(st);
	}
	
	/*get an student from db*/
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable(value = "id") Integer stId) {
		Student st = stuDAO.findOne(stId);
		if(st == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(st);
		}
	}
	
	/*update Student*/
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer stId , @Validated @RequestBody Student newSt) {
		
		Student st = stuDAO.findOne(stId);
		if(st == null) {
			return ResponseEntity.notFound().build();
		} else {
			st.setName(newSt.getName());
			st.setRoll_no(newSt.getRoll_no());
			
			Student updatedStudent = stuDAO.save(st);
			return ResponseEntity.ok(updatedStudent);
		}
		
	}
	
	
	/*Delete Student*/
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable(value = "id") Integer stId) {
		Student st = stuDAO.findOne(stId);
		if(st == null) {
			return ResponseEntity.notFound().build();
		} else {
			stuDAO.delete(st);
			return ResponseEntity.ok().build();
		}
		
	}
}	
