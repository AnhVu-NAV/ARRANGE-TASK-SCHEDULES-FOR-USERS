/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author vungu
 */

public class Customer {
    private String id;
    private String name;
    private String contactInformation;
    private List<Order> orderHistory;

    public Customer(String id, String name, String contactInformation, List<Order> orderHistory) {
        this.id = id;
        this.name = name;
        this.contactInformation = contactInformation;
        this.orderHistory = orderHistory;
    }

    public Customer() {
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

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", contactInformation=" + contactInformation + ", orderHistory=" + orderHistory + '}';
    }
    
}

