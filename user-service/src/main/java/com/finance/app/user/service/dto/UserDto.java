package com.finance.app.user.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {

    private Long pkUserId;

    @NotBlank(message = "Username must not be blank")
    private String title;

    @NotBlank(message = "Username must not be blank")
    private String firstName;

    @NotBlank(message = "Username must not be blank")
    private String lastName;

    @NotBlank(message = "Username must not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Username must not be blank")
    private String phoneNumber;

    public Long getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(Long pkUserId) {
        this.pkUserId = pkUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
