package com.payfever.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by richi on 2015.10.20..
 */
public class UserModel {
    private String mUserName = "";
    private String mPhoneNumber = "";
    private String mPushChannel;

    public UserModel() {}

    public UserModel(String _name, String _number) {
        mUserName = _name;
        mPhoneNumber = _number;
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

    public Map<String, String> getPostMap() {
        Map<String, String> map = new HashMap<>();
        map.put("userName", mUserName);
        map.put("phoneNumber", mPhoneNumber);

        return map;
    }
}
