package com.payfever.data.model.ringtone;

/**
 * Created by richi on 2015.11.02..
 */
public class Ringtone {
    private String mObjectId;
    private String mUrlToFile;
    private String mName;
    private int mRevenue;
    private boolean isAlreadyBuy;

    public String getObjectId() {
        return mObjectId;
    }

    public void setObjectId(String mObjectId) {
        this.mObjectId = mObjectId;
    }

    public String getUrlToFile() {
        return mUrlToFile;
    }

    public void setUrlToFile(String mUrlToFile) {
        this.mUrlToFile = mUrlToFile;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getRevenue() {
        return mRevenue;
    }

    public void setRevenue(int mRevenue) {
        this.mRevenue = mRevenue;
    }

    public boolean isAlreadyBuy() {
        return isAlreadyBuy;
    }

    public void setIsAlreadyBuy(boolean isAlreadyBuy) {
        this.isAlreadyBuy = isAlreadyBuy;
    }
}
