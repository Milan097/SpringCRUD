package com.myApp.springCRUD.service;

import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Student student = studentRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Student Not Found: " + userName));
        return UserDetailImpl.build(student);
    }

}
