package com.payfever.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by richi on 2015.10.20..
 */
public class UserModel {
    private String mUserName = "";
    private String mPhoneNumber = "";
    private String mPassword = "";
    private String mPushChannel;

    public UserModel() {}

    public UserModel(String _name, String _number, String _password) {
        mUserName = _name;
        mPhoneNumber = _number;
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

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public Map<String, String> getPostMap() {
        Map<String, String> map = new HashMap<>();
        map.put("userName", mUserName);
        map.put("phoneNumber", mPhoneNumber);
        map.put("password", mPassword);

        return map;
    }
}
