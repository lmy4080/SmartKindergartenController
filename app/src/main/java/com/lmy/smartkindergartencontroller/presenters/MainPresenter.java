package com.lmy.smartkindergartencontroller.presenters;

import com.lmy.smartkindergartencontroller.contracts.MainContract;
import com.lmy.smartkindergartencontroller.models.Images;
import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private ImageRepository mImageRepository;

    @Override
    public void attachView(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void setImageItems(ImageRepository imageRepository) {
        this.mImageRepository = imageRepository;
    }

    @Override
    public void loadItems() {
        ArrayList<Images> images = mImageRepository.getImages();
        mView.addItems(images);
        mView.notifyAdapter();
    }
}
