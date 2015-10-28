package com.payfever.data.model;

import java.util.Date;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatListModel {

    private String mUserName;
    private String mPhoneNumber;
    private String mPushChannel;
    private String mDecription;
    private Date mDate;

    public Date getDate() {
        return mDate;
    }

    public String getDecription() {
        return mDecription;
    }

    public String getPushChannel() {
        return mPushChannel;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setDecription(String mDecription) {
        this.mDecription = mDecription;
    }

    public void setPushChannel(String mPushChannel) {
        this.mPushChannel = mPushChannel;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}
