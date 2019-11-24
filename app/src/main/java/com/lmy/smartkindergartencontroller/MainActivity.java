package com.lmy.smartkindergartencontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lmy.smartkindergartencontroller.adapters.RecyclerAdapter;
import com.lmy.smartkindergartencontroller.contracts.MainContract;
import com.lmy.smartkindergartencontroller.models.Images;
import com.lmy.smartkindergartencontroller.networks.MqttClientHelper;
import com.lmy.smartkindergartencontroller.presenters.MainPresenter;
import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements testInterface, MainContract.View {

    private static final String TAG = "MainActivity";

    // vars
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        mRecyclerView = findViewById(R.id.recycler_view);

        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
        mPresenter.setImageItems(ImageRepository.getInstance());

        mAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mPresenter.loadItems();

        initMqttClient();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detachView();
    }

    @Override
    public void foo(String msg) {
        Log.d(TAG, "foo: subscribed called : " + msg);

        //mAdapter.modifyImages(msg);
        mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, 0);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList<Images> images) {
        mAdapter.setmImages(images);
    }

    @Override
    public void notifyAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    private void initMqttClient() {
        Log.d(TAG, "initMqttClient: init mqttclient");
        MqttClientHelper mqttClientHelper = new MqttClientHelper(this, this);
        mqttClientHelper.init();
    }
}
