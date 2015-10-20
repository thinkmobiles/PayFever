package com.payfever.data.model;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactModel {

    private String mName;
    private String mPhoneNumber;
    private int mStatus;
    private String mAvatar;

    public String getName() {
        return mName;
    }

    public void setName(final String _name) {
        this.mName = _name;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(final String _phoneNumber) {
        this.mPhoneNumber = _phoneNumber;
    }

    public int getStatus() {
        return mStatus;
    }
    public void setStatus(final int _status) {
        this.mStatus = _status;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(final String _avatar) {
        this.mAvatar = _avatar;
    }
}
