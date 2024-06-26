/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.time.LocalDateTime;
/**
 *
 * @author vungu
 */
public class Order {
    private String id;
    private int CustomerID;
    private String[] items;
    private LocalDateTime OrderDate;
    private LocalDateTime DeliveryDate;

    public Order() {
    }

    public Order(String id, int CustomerID, String[] items, LocalDateTime OrderDate, LocalDateTime DeliveryDate) {
        this.id = id;
        this.CustomerID = CustomerID;
        this.items = items;
        this.OrderDate = OrderDate;
        this.DeliveryDate = DeliveryDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public LocalDateTime getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDateTime OrderDate) {
        this.OrderDate = OrderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(LocalDateTime DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }
    
}
