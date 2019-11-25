package com.lmy.smartkindergartencontroller.presenters;

import android.content.Context;

import com.lmy.smartkindergartencontroller.networks.MqttClientHelperInterface;
import com.lmy.smartkindergartencontroller.contracts.MainContract;
import com.lmy.smartkindergartencontroller.models.Images;
import com.lmy.smartkindergartencontroller.networks.MqttClientHelper;
import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter, MqttClientHelperInterface {

    private static final String TAG = "MainPresenter";

    private MainContract.View mView;
    private ImageRepository mImageRepository;
    private MqttClientHelper mqttClientHelper;
    private Context mContext;

    public MainPresenter(Context context) {
        this.mContext = context;
    }

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

    @Override
    public void initMqttClient() {
        this.mqttClientHelper = new MqttClientHelper(mContext, this);
        mqttClientHelper.init();
    }

    @Override
    public void sendPayload(int flag, String payload) {
        mView.addItems(flag, payload);
        mView.notifyAdapter();
    }
}
