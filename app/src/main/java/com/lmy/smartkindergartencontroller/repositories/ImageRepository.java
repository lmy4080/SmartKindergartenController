package com.lmy.smartkindergartencontroller.repositories;

import com.lmy.smartkindergartencontroller.models.Images;

import java.util.ArrayList;

/**
 * singleton class
 */
public class ImageRepository {

    private static ImageRepository instance;
    private ArrayList<Images> dataSet = new ArrayList<>();

    public static ImageRepository getInstance() {
        if(instance == null) {
            instance = new ImageRepository();
        }

        return instance;
    }

    public ArrayList<Images> getImages() {
        setImages();
        return dataSet;
    }

    public void setImages() {
        dataSet.add(new Images("Temp", "https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png"));
        dataSet.add(new Images("Humid", "https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png"));
        dataSet.add(new Images("Led", "https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png"));
        dataSet.add(new Images("Motor", "https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png"));
        dataSet.add(new Images("Video", "https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png"));
        dataSet.add(new Images("Ultra", "https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png"));
    }
}
