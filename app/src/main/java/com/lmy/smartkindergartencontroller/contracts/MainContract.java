package com.lmy.smartkindergartencontroller.contracts;

import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

public interface MainContract {

    interface View {

        void switchImageItem(int position);

        void updateScreen();
    }

    interface Presenter {

        void attachView(View view);

        void setRecyclerAdapterModel(RecyclerAdapterContract.Model adapterModel);

        void setRecyclerAdapterView(RecyclerAdapterContract.View adapterView);

        void detachView();

        void setImageItems(ImageRepository imageRepository);

        void loadItems();

        void initMqttClient();
    }
}
