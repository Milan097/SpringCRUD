package com.myApp.springCRUD.model.responseModel;

import java.util.List;

public class LoginResponse {

    private String jwtToken;
    private String type = "Bearer";
    private Integer id;
    private String name;
    private List<String> roles;

    public LoginResponse(String jwtToken, Integer id, String name, List<String> roles) {
        this.jwtToken = jwtToken;
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
