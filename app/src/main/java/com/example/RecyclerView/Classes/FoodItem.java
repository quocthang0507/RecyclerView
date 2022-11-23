package com.example.RecyclerView.Classes;

/**
 * The FoodItem class
 */
public class FoodItem {

    public int ID;
    public String Name;
    public int Price;
    public String ImgThumb;
    public String Unit;

    public FoodItem(int id, String name, int price, String imgThumb, String unit) {
        this.ID = id;
        this.ImgThumb = imgThumb;
        this.Name = name;
        this.Price = price;
        this.Unit = unit;
    }
}
