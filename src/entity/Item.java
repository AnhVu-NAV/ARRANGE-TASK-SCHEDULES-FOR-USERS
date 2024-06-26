/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author vungu
 */
public class Item {
    private String ID;
    private String Name;
    private String Category;
    private int stockLevel;
    private int price;

    public Item() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item(String ID, String Name, String Category, int stockLevel, int price) {
        this.ID = ID;
        this.Name = Name;
        this.Category = Category;
        this.stockLevel = stockLevel;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "ID=" + ID + ", Name=" + Name + ", Category=" + Category + ", stockLevel=" + stockLevel + ", price=" + price + '}';
    }

}