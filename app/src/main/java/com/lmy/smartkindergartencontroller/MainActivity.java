package com.lmy.smartkindergartencontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public void switchImageItem(int position) {

        if(position == 2) { // LED
            if(mLedIsOn) { // if Led is on,
                mAdapter.switchImage(position, "LED : ON", imageUrl);
            }
            else { // if Led is off,

            }
        }

        if(position == 3) { // MOTOR
            if(mMotorIsOn) { // if Motor is on,

            }
            else { // if Motor is off,

            }
        }
    }
}
