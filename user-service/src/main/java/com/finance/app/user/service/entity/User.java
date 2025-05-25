package com.finance.app.user.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "iam_user")
public class User {

    /**
     * The pk user id.
     */
    @Column(name = "pk_user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pkUserId;

    /**
     * The title.
     */
    @Column(name = "title")
    private String title;

    /**
     * The first name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The email.
     */
    @Column(name = "email")
    private String email;

    /**
     * The phone number.
     */
    @Column(name = "phone_number")
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
