package com.payfever.data.model.network;

import java.util.List;

/**
 * Created by richi on 2015.10.21..
 */
public class Statistic {
    private int mSentOut;
    private int mPending;
    private int mExpired;
    private int mTotalNetworkAccepts;
    private int mFirstLevelAccepts;
    private int mNetworkLevelAccepts;

    private List<NetworkUser> mUsers;

    public List<NetworkUser> getmUsers() {
        return mUsers;
    }

    public void setmUsers(List<NetworkUser> mUsers) {
        this.mUsers = mUsers;
    }

    public String getmSentOut() {
        return String.valueOf(mSentOut);
    }

    public int getSentOut() {
        return mSentOut;
    }

    public void setmSentOut(int mSentOut) {
        this.mSentOut = mSentOut;
    }

    public String getmPending() {
        return String.valueOf(mPending);
    }

    public void setmPending(int mPending) {
        this.mPending = mPending;
    }

    public String getmExpired() {
        return String.valueOf(mExpired);
    }

    public void setmExpired(int mExpired) {
        this.mExpired = mExpired;
    }

    public String getmTotalNetworkAccepts() {
        return String.valueOf(mTotalNetworkAccepts);
    }

    public void setmTotalNetworkAccepts(int mTotalNetworkAccepts) {
        this.mTotalNetworkAccepts = mTotalNetworkAccepts;
    }

    public String getmFirstLevelAccepts() {
        return String.valueOf(mFirstLevelAccepts);
    }

    public void setmFirstLevelAccepts(int mFirstLevelAccepts) {
        this.mFirstLevelAccepts = mFirstLevelAccepts;
    }

    public String getmNetworkLevelAccepts() {
        return String.valueOf(mNetworkLevelAccepts);
    }

    public void setmNetworkLevelAccepts(int mNetworkLevelAccepts) {
        this.mNetworkLevelAccepts = mNetworkLevelAccepts;
    }
}
