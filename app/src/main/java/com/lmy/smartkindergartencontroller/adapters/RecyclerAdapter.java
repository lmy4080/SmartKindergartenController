package com.lmy.smartkindergartencontroller.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lmy.smartkindergartencontroller.R;
import com.lmy.smartkindergartencontroller.models.Images;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private ArrayList<Images> mImages = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Images> getmImages() {
        return mImages;
    }

    public void setmImages(ArrayList<Images> mImages) {
        this.mImages = mImages;
    }

//    public void modifyImages(String text) {
//        Log.d(TAG, "modifyImages: called.");
//
//        mImages.set(2, new Images(text, "https://cdn1.iconfinder.com/data/icons/business-5/512/light_bulb_7-512.png"));
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_griditem, parent, false);
        ViewHolder holder = new ViewHolder(view);

        int weight = 3; //number of parts in the recycler view.
        view.getLayoutParams().height = parent.getHeight() / weight;

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position).getImageUrl())
                .into(holder.image);

        holder.title.setText(mImages.get(position).getTitle());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clied on: " + position);
                Toast.makeText(mContext, mImages.get(position).getImageUrl(), Toast.LENGTH_SHORT).show();
                //MqttClientHelper.getInstance().publish(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.layout_parent);
        }
    }
}
