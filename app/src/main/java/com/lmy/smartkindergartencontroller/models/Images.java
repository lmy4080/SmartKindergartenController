package com.lmy.smartkindergartencontroller.models;

public class Images {

    private String mTitle;
    private int mImageResId;

    public Images(String mTitle, int mImageResId) {
        this.mTitle = mTitle;
        this.mImageResId = mImageResId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public void setImageResId(int mImageResId) {
        this.mImageResId = mImageResId;
    }
}
