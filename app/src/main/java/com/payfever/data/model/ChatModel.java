package com.payfever.data.model;

import java.util.Date;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public final class ChatModel {

    private String mUserName;
    private String mDescription;
    private String mPushChannel;
    private String mDate;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String _userName) {
        this.mUserName = _userName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String _date) {
        this.mDate = _date;
    }

    public String getPushChannel() {
        return mPushChannel;
    }

    public void setPushChannel(String _pushChannel) {
        this.mPushChannel = _pushChannel;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String _desctiption) {
        this.mDescription = _desctiption;
    }
}
