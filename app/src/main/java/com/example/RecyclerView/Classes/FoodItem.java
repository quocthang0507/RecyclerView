package com.example.RecyclerView.Classes;

/**
 * The FoodItem class
 */
public class FoodItem {

    public int ID;
    public String Name;
    public int Price;
    public String ImgThumbPath;
    public String Unit;

    public FoodItem(int id, String name, int price, String imgThumbPath, String unit) {
        this.ID = id;
        this.ImgThumbPath = imgThumbPath;
        this.Name = name;
        this.Price = price;
        this.Unit = unit;
    }
}
