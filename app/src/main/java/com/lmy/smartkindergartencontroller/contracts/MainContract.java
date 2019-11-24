package com.lmy.smartkindergartencontroller.contracts;

import com.lmy.smartkindergartencontroller.models.Images;
import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

import java.util.ArrayList;

public interface MainContract {

    interface View {

        void addItems(ArrayList<Images> images);

        void notifyAdapter();
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void setImageItems(ImageRepository imageRepository);

        void loadItems();

    }
}
