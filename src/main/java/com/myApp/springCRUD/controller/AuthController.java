package com.myApp.springCRUD.controller;

import com.myApp.springCRUD.model.Address;
import com.myApp.springCRUD.model.EnumRole;
import com.myApp.springCRUD.model.Role;
import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.model.requestModel.LoginRequest;
import com.myApp.springCRUD.model.requestModel.StudentAddress;
import com.myApp.springCRUD.model.responseModel.LoginResponse;
import com.myApp.springCRUD.model.responseModel.MessageResponse;
import com.myApp.springCRUD.repository.RoleRepository;
import com.myApp.springCRUD.repository.StudentRepository;
import com.myApp.springCRUD.service.StudentDAO;
import com.myApp.springCRUD.service.UserDetailImpl;
import com.myApp.springCRUD.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/school/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;


    // End point to Login
    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("User is Disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Username or Password", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetail.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new LoginResponse(
                        jwt,
                        userDetail.getId(),
                        userDetail.getUsername(),
                        roles
                )
        );
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody StudentAddress studentAddress) throws Exception {
        if (studentRepository.existsByName(studentAddress.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Student student = new Student(studentAddress.getName(), studentAddress.getRollNo(), encoder.encode(studentAddress.getPassword()));
        Address address = studentAddress.getAddress();

        Set<String> strRoles = studentAddress.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(
                s -> {
                    if(s.equals("admin")) {
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    } else {
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            );
        }

        student.setRoles(roles);
        studentDAO.createStudent(student, address);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
