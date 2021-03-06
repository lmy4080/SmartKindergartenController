package com.lmy.smartkindergartencontroller.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lmy.smartkindergartencontroller.contracts.RecyclerAdapterContract;
import com.lmy.smartkindergartencontroller.listeners.OnItemClickListener;
import com.lmy.smartkindergartencontroller.networks.MqttClientHelperInterface;
import com.lmy.smartkindergartencontroller.contracts.MainContract;
import com.lmy.smartkindergartencontroller.models.Images;
import com.lmy.smartkindergartencontroller.networks.MqttClientHelper;
import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter, MqttClientHelperInterface, OnItemClickListener {

    private static final String TAG = "MainPresenter";

    private MainContract.View mView;

    private ImageRepository mImageRepository;
    private MqttClientHelper mqttClientHelper;
    private Context mContext;
    private RecyclerAdapterContract.Model mAdapterModel;
    private RecyclerAdapterContract.View mAdapterView;

    public MainPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void setRecyclerAdapterModel(RecyclerAdapterContract.Model adapterModel) {
        this.mAdapterModel = adapterModel;
    }

    @Override
    public void setRecyclerAdapterView(RecyclerAdapterContract.View adapterView) {
        this.mAdapterView = adapterView;
        this.mAdapterView.setOnClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: called. position : " + position);
        if(mqttClientHelper.publish(position, mView.getSensorStatus(position))) {
            mView.switchImageItem(position, -1);
            mView.updateScreen();
            mAdapterView.notifyAdapter();
        }
        else {

        }
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
        mAdapterModel.setmImages(images);
        mAdapterView.notifyAdapter();
    }

    @Override
    public void initMqttClient() {
        this.mqttClientHelper = new MqttClientHelper(mContext, this);
        mqttClientHelper.init();
    }

    @Override
    public void sendPayload(int flag, String payload) {
        Log.d(TAG, "sendPayload: called.");
        if(flag == 5) { // Ultra, Parking
            mView.switchImageItem(flag, Integer.parseInt(payload));
            mView.updateScreen();
            mAdapterView.notifyAdapter();
        }
        else {
            mAdapterModel.setmImages(flag, payload);
            mView.updateScreen();
            mAdapterView.notifyAdapter();
        }
    }

    @Override
    public void moveVideoSite(String videoUrl) {
        Log.d(TAG, "moveVideoSite: called.");
        if(videoUrl != null) {
            mView.moveVideoSite(videoUrl);
        }
        else {
            Toast.makeText(mContext, "Url 정보를 받아오지 못했습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
