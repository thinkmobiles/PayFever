package com.payfever.data.model;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public enum ContactStatus {

    UNCHECKED(0), CHECKED(1), PENDING(2), EXPIRED(3);

    private int mStatus;

    public int getStatus() {
        return mStatus;
    }

    ContactStatus(int _status) {
        mStatus = _status;
    }
}
