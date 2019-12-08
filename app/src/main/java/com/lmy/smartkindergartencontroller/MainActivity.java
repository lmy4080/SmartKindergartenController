package com.lmy.smartkindergartencontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.lmy.smartkindergartencontroller.adapters.RecyclerAdapter;
import com.lmy.smartkindergartencontroller.contracts.MainContract;
import com.lmy.smartkindergartencontroller.presenters.MainPresenter;
import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";

    // vars
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private MainContract.Presenter mPresenter;

    // vars for sensors
    private boolean mLedIsOn = false;
    private boolean mMotorIsOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        mRecyclerView = findViewById(R.id.recycler_view);

        mAdapter = new RecyclerAdapter(this);

        mPresenter = new MainPresenter(this);
        mPresenter.attachView(this);

        mPresenter.setRecyclerAdapterModel(mAdapter);
        mPresenter.setRecyclerAdapterView(mAdapter);
        mPresenter.setImageItems(ImageRepository.getInstance());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mPresenter.loadItems();

        mPresenter.initMqttClient();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detachView();
    }

    @Override
    public void updateScreen() {

        mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, 0);
    }

    @Override
    public void switchImageItem(int position, int flag) {

        if(position == 2) { // LED, Lights
            if(mLedIsOn) { // if Led is on, turn it off
                mAdapter.switchImage(position, "LIGHT : OFF", R.drawable.led_off);
                mLedIsOn = false;
            }
            else { // if Led is off, turn it on
                mAdapter.switchImage(position, "LIGHT :  ON", R.drawable.led_on);
                mLedIsOn = true;
            }
        }

        if(position == 3) { // MOTOR, Curtains
            if(mMotorIsOn) { // if Motor is on, turn it off
                mAdapter.switchImage(position, "CURTAINS : OFF", R.drawable.curtain_off);
                mMotorIsOn = false;
            }
            else { // if Motor is off, turn it on
                mAdapter.switchImage(position, "CURTAINS :  ON", R.drawable.curtain_on);
                mMotorIsOn = true;
            }
        }

        if(position == 5) { // ULTRA, Parking
            Log.d(TAG, "switchImageItem: called. pos : " + position);
            if(flag == 0) { // if Parking lot is vacant,
                mAdapter.switchImage(position, "PARKING : VACANT", R.drawable.parking_off);
            }
            else { // if Parking lot is occupied,
                mAdapter.switchImage(position, "PARKING :   FULL", R.drawable.parking_on);
            }
        }
    }

    @Override
    public void moveVideoSite(String videoUrl) {

        Uri uri = Uri.parse(videoUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Log.d(TAG, "moveVideoSite: " + videoUrl);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean getSensorStatus(int position) {
        switch(position) {
            case 2:
                return mLedIsOn;
            case 3:
                return mMotorIsOn;
        }

        return false;
    }
}
