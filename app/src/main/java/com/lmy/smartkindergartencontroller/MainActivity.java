package com.lmy.smartkindergartencontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lmy.smartkindergartencontroller.adapters.RecyclerAdapter;
import com.lmy.smartkindergartencontroller.repositories.MqttClientHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // vars
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        for(int i=0; i<6; i++) {
            mImageUrls.add("https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png");
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerAdapter adapter = new RecyclerAdapter(mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        initMqttClient();
    }

    private void initMqttClient() {
        Log.d(TAG, "initMqttClient: init mqttclient");
        MqttClientHelper.getInstance().init(this);
    }
}
