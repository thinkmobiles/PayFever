package com.payfever.data.model.network;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkUser {
    private String mUserName;
    private boolean mIsFirstLevel;
    private String mPushChannel;
    private String mPhoneNumber;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String muserName) {
        this.mUserName = muserName;
    }

    public boolean ismIsFirstLevel() {
        return mIsFirstLevel;
    }

    public void setmIsFirstLevel(boolean mIsFirstLevel) {
        this.mIsFirstLevel = mIsFirstLevel;
    }

    public String getmPushChannel() {
        return mPushChannel;
    }

    public void setmPushChannel(String mPushChannel) {
        this.mPushChannel = mPushChannel;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}
