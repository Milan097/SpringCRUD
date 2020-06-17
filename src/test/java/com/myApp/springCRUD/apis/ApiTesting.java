package com.myApp.springCRUD.apis;

import com.myApp.springCRUD.model.Student;
import com.myApp.springCRUD.repository.AddressRepository;
import com.myApp.springCRUD.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTesting {
    final String baseUrl = "http://localhost:8080/school";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testGetStudentById() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        String testUrl = baseUrl + "/student/id/55";
        URI uri = new URI(testUrl);

        ResponseEntity<Student> result = restTemplate.getForEntity(uri, Student.class);
        System.out.println(result.getBody().getName());
        Assert.assertEquals(result.getBody().getClass(), Student.class);
        Assert.assertEquals(result.getBody().getId(), 55);
        Assert.assertEquals(result.getBody().getName(), "Milan Thummar");
    }

    @Test
    public void testGetStudentByRollNo() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        String testUrl = baseUrl + "/student/rollNo/117";
        URI uri = new URI(testUrl);

        ResponseEntity<Student> result = restTemplate.getForEntity(uri, Student.class);
        System.out.println(result.getBody().getName());
        Assert.assertEquals(result.getBody().getClass(), Student.class);
        Assert.assertEquals(result.getBody().getRollNo(), 117);
        Assert.assertEquals(result.getBody().getName(), "Milan Thummar");
    }

}
