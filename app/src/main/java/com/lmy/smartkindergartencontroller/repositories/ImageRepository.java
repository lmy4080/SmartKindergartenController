package com.lmy.smartkindergartencontroller.repositories;

import com.lmy.smartkindergartencontroller.R;
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
        dataSet.add(new Images("TEMP : 0", R.drawable.temp));
        dataSet.add(new Images("HUMID : 0", R.drawable.humid));
        dataSet.add(new Images("LIGHT : OFF", R.drawable.led_off));
        dataSet.add(new Images("CURTAINS : OFF", R.drawable.curtain_off));
        dataSet.add(new Images("CCTV : ", R.drawable.cctv));
        dataSet.add(new Images("PARKING : VACANT", R.drawable.parking_off));
    }
}
