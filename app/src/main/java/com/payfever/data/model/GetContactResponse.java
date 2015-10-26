package com.payfever.data.model;

import java.util.List;

/**
 * Created by richi on 2015.10.26..
 */
public class GetContactResponse {
    private List<String> mAll;
    private List<String> mPending;
    private List<String> mExpired;

    public List<String> getmAll() {
        return mAll;
    }

    public void setmAll(List<String> mAll) {
        this.mAll = mAll;
    }

    public List<String> getmPending() {
        return mPending;
    }

    public void setmPending(List<String> mPending) {
        this.mPending = mPending;
    }

    public List<String> getmExpired() {
        return mExpired;
    }

    public void setmExpired(List<String> mExpired) {
        this.mExpired = mExpired;
    }
}
