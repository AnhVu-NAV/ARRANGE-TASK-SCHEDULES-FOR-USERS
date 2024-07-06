/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author vungu
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L; // Add serialVersionUID
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    public User() {
    }

    public User(String id, String name, String email, String phoneNumber, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return  id + ", " + name + ", " + email + ", " + phoneNumber + ", " + password;
    }
}
