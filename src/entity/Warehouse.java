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

public class Warehouse {
    private String id;
    private String location;
    private int capacity;
    private List<Item> itemsStored;


    public Warehouse(String id, String location, int capacity, List<Item> itemsStored) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.itemsStored = itemsStored;
    }

    public Warehouse() {
    }

  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItemsStored() {
        return itemsStored;
    }

    public void setItemsStored(List<Item> itemsStored) {
        this.itemsStored = itemsStored;
    }

    @Override
    public String toString() {
        return "Warehouse{" + "id=" + id + ", location=" + location + ", capacity=" + capacity + ", itemsStored=" + itemsStored + '}';
    }
    
}

