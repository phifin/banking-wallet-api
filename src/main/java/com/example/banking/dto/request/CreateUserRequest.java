package com.example.banking.dto.request;

public class CreateUserRequest {
    private String fullName;
    private String email;
    private String phone;

    public CreateUserRequest() {

    }

    public CreateUserRequest(String fullName, String email, String phone) {
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
