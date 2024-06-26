/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author vungu
 */
import java.util.List;

public class Supplier {
    private String id;
    private String name;
    private String contactInformation;
    private List<Item> itemsSupplied;

 
    public Supplier(String id, String name, String contactInformation, List<Item> itemsSupplied) {
        this.id = id;
        this.name = name;
        this.contactInformation = contactInformation;
        this.itemsSupplied = itemsSupplied;
    }

    public Supplier() {
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

    public List<Item> getItemsSupplied() {
        return itemsSupplied;
    }

    public void setItemsSupplied(List<Item> itemsSupplied) {
        this.itemsSupplied = itemsSupplied;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", name=" + name + ", contactInformation=" + contactInformation + ", itemsSupplied=" + itemsSupplied + '}';
    }
    
}

