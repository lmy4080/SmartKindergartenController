package com.lmy.smartkindergartencontroller.contracts;

import com.lmy.smartkindergartencontroller.repositories.ImageRepository;

public interface MainContract {

    interface View {

        void updateScreen();

        void switchImageItem(int position);

        void moveVideoSite(String videoUrl);
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
