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
    private String id;
    private String name;
    private String Category;
    private int StockLevel;
    private int price;

    public Item() {
    }

    public Item(String id, String name, String Category, int StockLevel, int price) {
        this.id = id;
        this.name = name;
        this.Category = Category;
        this.StockLevel = StockLevel;
        this.price = price;
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getStockLevel() {
        return StockLevel;
    }

    public void setStockLevel(int StockLevel) {
        this.StockLevel = StockLevel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
