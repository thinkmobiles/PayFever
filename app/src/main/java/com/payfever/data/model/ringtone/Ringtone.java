package com.payfever.data.model.ringtone;

/**
 * Created by richi on 2015.11.02..
 */
public class Ringtone {
    private String mObjectId;
    private String mUrlToFile;
    private String mName;
    private int mRevenue;

    public String getmObjectId() {
        return mObjectId;
    }

    public void setmObjectId(String mObjectId) {
        this.mObjectId = mObjectId;
    }

    public String getmUrlToFile() {
        return mUrlToFile;
    }

    public void setmUrlToFile(String mUrlToFile) {
        this.mUrlToFile = mUrlToFile;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmRevenue() {
        return mRevenue;
    }

    public void setmRevenue(int mRevenue) {
        this.mRevenue = mRevenue;
    }
}
