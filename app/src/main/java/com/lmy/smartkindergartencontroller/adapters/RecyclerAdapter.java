package com.lmy.smartkindergartencontroller.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lmy.smartkindergartencontroller.R;
import com.lmy.smartkindergartencontroller.contracts.RecyclerAdapterContract;
import com.lmy.smartkindergartencontroller.listeners.OnItemClickListener;
import com.lmy.smartkindergartencontroller.models.Images;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements RecyclerAdapterContract.Model, RecyclerAdapterContract.View {

    private static final String TAG = "RecyclerAdapter";
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<Images> mImages = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Images> getmImages() {
        return mImages;
    }

    public void switchImage(int flag, String title, int imageResId) {
        this.mImages.set(flag, new Images(title, imageResId));
    }

    public void setmImages(ArrayList<Images> mImages) {
        this.mImages = mImages;
    }

    @Override
    public void setmImages(int flag, String payload) {
        this.mImages.set(flag, new Images(payload, mImages.get(flag).getImageResId()));
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnClickListener(OnItemClickListener clickListener) {
        this.mOnItemClickListener = clickListener;
    }

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position).getImageResId())
                .into(holder.image);

        holder.title.setText(mImages.get(position).getTitle());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);

                if(holder.onItemClickListener != null) {
                    holder.onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private OnItemClickListener onItemClickListener;

        private TextView title;
        private ImageView image;
        private RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.onItemClickListener = mOnItemClickListener;

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.layout_parent);
        }
    }
}
