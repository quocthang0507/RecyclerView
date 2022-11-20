package com.example.RecyclerView.Classes;

import java.util.ArrayList;

/**
 * The collection of FoodItem so it can assume that it is a database
 */
public class Dataset {

    private ArrayList<FoodItem> dataset;
    private static Dataset instance;

    public Dataset() {
        this.dataset = new ArrayList<>();
    }

    // Singleton pattern
    public static Dataset getInstance() {
        if (instance == null)
            instance = new Dataset();
        return instance;
    }

    public ArrayList<FoodItem> getDataset() {
        return dataset;
    }

    public int getSize() {
        return dataset.size();
    }

    public void importDataset(ArrayList<FoodItem> items) {
        dataset = items;
    }

    public void add(FoodItem item) {
        dataset.add(item);
    }

    public FoodItem getById(int id) {
        return dataset.get(id);
    }

    public void update(FoodItem item) {
        getById(item.ID).Name = item.Name;
        getById(item.ID).Price = item.Price;
        getById(item.ID).Unit = item.Unit;
//        getById(item.ID).ImgThumbPath = item.ImgThumbPath;
    }

    public void clear() {
        dataset.clear();
    }

    public int getMaxId() {
        return dataset.get(getSize() - 1).ID;
    }
}
