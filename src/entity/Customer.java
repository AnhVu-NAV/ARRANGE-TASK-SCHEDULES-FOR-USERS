/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author vungu
 */
public class Customer {
    private String id;
    private String name;
    private String contact;
    private String[] orderHistory;

    public Customer() {
    }

    public Customer(String id, String name, String contact, String[] orderHistory) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.orderHistory = orderHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String[] getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(String[] orderHistory) {
        this.orderHistory = orderHistory;
    }
    
}
