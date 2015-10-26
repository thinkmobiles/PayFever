package com.payfever.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by richi on 2015.10.20..
 */
public class UserModel extends LogInUserModel {
    private String mPhoneNumber = "";

    public UserModel() {}

    public UserModel(String _name, String _number, String _password) {
        setUserName(_name);
        mPhoneNumber = _number;
        setPassword(_password);
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public Map<String, String> getPostMap() {
        Map<String, String> map = new HashMap<>();
        map.put("userName", getUserName());
        map.put("phoneNumber", mPhoneNumber);
        map.put("password", getPassword());

        return map;
    }
}
