package com.lmy.smartkindergartencontroller.contracts;

import com.lmy.smartkindergartencontroller.listeners.OnItemClickListener;
import com.lmy.smartkindergartencontroller.models.Images;

import java.util.ArrayList;

public interface RecyclerAdapterContract {

    interface View {

        void setOnClickListener(OnItemClickListener clickListener);

        void notifyAdapter();
    }

    interface Model {

        void setmImages(ArrayList<Images> mImages);

        void setmImages(int flag, String payload);
    }
}
