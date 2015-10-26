package com.payfever.data.model;

/**
 * Created by richi on 2015.10.26..
 */
public class LogInUserModel {
    private String mUserName = "";
    private String mPassword = "";
    private String mPushChannel;

    public LogInUserModel() {}

    public LogInUserModel(String _name, String _password) {
        mUserName = _name;
        mPassword = _password;
    }

    public String getPushChannel() {
        return mPushChannel;
    }

    public void setPushChannel(String mPushChannel) {
        this.mPushChannel = mPushChannel;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }


    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

}
