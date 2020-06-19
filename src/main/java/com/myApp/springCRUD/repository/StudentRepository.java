package com.myApp.springCRUD.repository;

import com.myApp.springCRUD.model.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByRollNo(int rollNo);

    Optional<Student> findByName(String name);

    Boolean existsByName(String username);
}
