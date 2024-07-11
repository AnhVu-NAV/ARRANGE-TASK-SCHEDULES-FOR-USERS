/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author AnhVu
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password; // Store hashed password

    public User(){
    }

    public User(String id, String name, String email, String phoneNumber, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = MD5Encryption(password); // Encrypt password on creation
    }
    
    // Constructor from String
    public User(String data) {
        String[] parts = data.split(", ");
        this.id = parts[0];
        this.name = parts[1];
        this.email = parts[2];
        this.phoneNumber = parts[3];
        this.password = parts[4];
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

    public void setPassword(String password) {
        this.password = MD5Encryption(password);
    }

    private static String MD5Encryption(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(MD5Encryption(password));
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + email + ", " + phoneNumber + ", " + password;
    }
}