package com.myApp.springCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myApp.springCRUD.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
