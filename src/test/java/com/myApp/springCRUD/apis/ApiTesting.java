package com.myApp.springCRUD.apis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import io.jsonwebtoken.SignatureException;

@SuppressWarnings("RedundantThrows")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApiTesting {
    @Autowired
    protected MockMvc mockMvc;

    String apiToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWxhbiIsImV4cCI6MTU5MjQ5NjQzNiwiaWF0IjoxNTkyNDYwNDM2fQ.2kMViSoHVy43fCrOjoHJK9Aqc_CfoVCAoJVVdnJhoDE";
    // To Test Login Api
    @Test
    public void testUserLogin() throws Exception {
        try {
            String data = "{\n" +
                    "    \"userName\" : \"milan\",\n" +
                    "    \"password\" : \"milan\"\n" +
                    "}";
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            apiToken = resultActions.andReturn().getResponse().getContentAsString();
            System.out.println("\n"+apiToken+"\n");
            apiToken = apiToken.substring(13,apiToken.length()-2);
            System.out.println("\n"+apiToken+"\n");
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    // Invalid Password at Login
    @Test
    public void testUserLoginInvalidPassword() throws Exception {
        try {
            String data = "{\n" +
                    "    \"userName\" : \"milan\",\n" +
                    "    \"password\" : \"milan123\"\n" +
                    "}";
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isForbidden());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    // To test Get Student using Jwt Token
    @Test
    public void testGetStudentByIdJwtToken() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/school/student/id/55")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer "+apiToken));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Milan Thummar"));
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.rollNo").value("117"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetStudentByIdJwtTokenWithInvalidToken() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/school/student/id/55")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer "+apiToken+"158"));

            resultActions.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        } catch (SignatureException e) {
            System.out.println("Invalid Token");
            e.printStackTrace();
        }
    }




    // All Test Below are For No security if Token based Scheme is there then this test cases are failed.
    // To Test Get Request for Student by id
    @Test
    public void testGetStudentById() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/school/student/id/55")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Milan Thummar"));
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.rollNo").value("117"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To test Get Request on Invalid API
    @Test
    public void testGetStudentByIdMethodNotAllowed() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/school/student/55")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Get Request when given student_id is Not Found
    @Test
    public void testGetStudentByIdNotFound() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/school/student/id/57")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Get Request for Student by rollNo
    @Test
    public void testGetStudentByRollNo() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/school/student/rollNo/117")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Milan Thummar"));
            resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.rollNo").value("117"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Get Request for All Address of Student
    // To Test Get Request when given student_id is Not Found
    @Test
    public void testGetAddressOfStudentByRollNoNotFound() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get("/school/student/rollNo/78/address")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Post Request For Add Student
    @Test
    public void testPostStudent() throws Exception {
        try {
            String data = "{\n" +
                    "    \"name\" : \"Rohit Sharma\",\n" +
                    "    \"rollNo\" : 118,\n" +
                    "    \"address\" : {\n" +
                    "        \"line1\" : \"Ganga Park 2\",\n" +
                    "        \"line2\" : \"varachha\",\n" +
                    "        \"line3\" : \"Surat\",\n" +
                    "        \"pinCode\" : 395006\n" +
                    "    }\n" +
                    "}";
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/student")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Post Request For Add Student with rollNo which is in use
    // RollNo must be unique so test what if we not give unique roll no
    @Test
    public void testPostStudentRollNoMustBeUnique() throws Exception {
        try {
            // Here rollNo 117 is already present in database
            String data = "{\n" +
                    "    \"name\" : \"Rohit Sharma\",\n" +
                    "    \"rollNo\" : 117,\n" +
                    "    \"address\" : {\n" +
                    "        \"line1\" : \"Ganga Park 2\",\n" +
                    "        \"line2\" : \"varachha\",\n" +
                    "        \"line3\" : \"Surat\",\n" +
                    "        \"pinCode\" : 395006\n" +
                    "    }\n" +
                    "}";
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/student")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));
            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (NestedServletException e) {
            System.out.println("\n" + e.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // For post request name , rollNo , address->line1 , address->pincode must not null.
    @Test
    public void testPostStudentNotNull() throws Exception {
        try {
            // Here rollNo and name is null
            String data = "{\n" +
                    "    \"address\" : {\n" +
                    "        \"line1\" : \"Ganga Park 2\",\n" +
                    "        \"line2\" : \"varachha\",\n" +
                    "        \"line3\" : \"Surat\",\n" +
                    "        \"pinCode\" : 395006\n" +
                    "    }\n" +
                    "}";
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/student")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));
            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (NestedServletException e) {
            System.out.println("\n" + e.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Post Request For Add Address for Student
    @Test
    public void testPostAddress() throws Exception {
        try {
            String data = "{\n" +
                    "        \"line1\" : \"Apple Heights\",\n" +
                    "        \"line2\" : \"Andheri\",\n" +
                    "        \"line3\" : \"Mumbai\",\n" +
                    "        \"pinCode\" : 123456\n" +
                    "      }" ;
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/student/id/68/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Post Request For Add Address for Student which is not present in database
    @Test
    public void testPostAddressStudentIdNotFound() throws Exception {
        try {
            String data = "{\n" +
                    "        \"line1\" : \"Apple Heights\",\n" +
                    "        \"line2\" : \"Andheri\",\n" +
                    "        \"line3\" : \"Mumbai\",\n" +
                    "        \"pinCode\" : 123456\n" +
                    "      }" ;
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/student/id/78/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Post Request For Add Address for Student which is not present in database
    @Test
    public void testPostAddressStudentRollNoNotFound() throws Exception {
        try {
            String data = "{\n" +
                    "        \"line1\" : \"Apple Heights\",\n" +
                    "        \"line2\" : \"Andheri\",\n" +
                    "        \"line3\" : \"Mumbai\",\n" +
                    "        \"pinCode\" : 123456\n" +
                    "      }" ;
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/student/rollNo/78/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Put Request For Update Student
    @Test
    public void testPutStudent() throws Exception {
        try {
            String data = "{\n" +
                    "    \"name\" : \"Rohit-man Sharma\",\n" +
                    "    \"rollNo\" : 118\n" +
                    "      }" ;
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.put("/school/student/68")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Put Request For Update Student where Student not exist
    @Test
    public void testPutStudentIdNotFound() throws Exception {
        try {
            String data = "{\n" +
                    "    \"name\" : \"Rohit-man Sharma\",\n" +
                    "    \"rollNo\" : 118\n" +
                    "      }" ;
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.put("/school/student/7")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Delete Request For Student
    @Test
    public void testDeleteStudent() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.delete("/school/student/68")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Response is " + resultActions.andReturn().getResponse().getContentAsString());
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Delete Request For Student which is not exists
    @Test
    public void testDeleteStudentIdNotFound() throws Exception {
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.delete("/school/student/7")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Bad Request
    @Test
    public void testPutBadRequest() throws Exception{
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.put("/school/student/7")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To Test Bad Request
    @Test
    public void testPostBadRequest() throws Exception{
        try {
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/school/student")
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Status is " + resultActions.andReturn().getResponse().getStatus());
            resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
