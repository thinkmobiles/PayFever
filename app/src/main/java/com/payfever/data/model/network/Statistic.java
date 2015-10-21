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

    public int getmSentOut() {
        return mSentOut;
    }

    public void setmSentOut(int mSentOut) {
        this.mSentOut = mSentOut;
    }

    public int getmPending() {
        return mPending;
    }

    public void setmPending(int mPending) {
        this.mPending = mPending;
    }

    public int getmExpired() {
        return mExpired;
    }

    public void setmExpired(int mExpired) {
        this.mExpired = mExpired;
    }

    public int getmTotalNetworkAccepts() {
        return mTotalNetworkAccepts;
    }

    public void setmTotalNetworkAccepts(int mTotalNetworkAccepts) {
        this.mTotalNetworkAccepts = mTotalNetworkAccepts;
    }

    public int getmFirstLevelAccepts() {
        return mFirstLevelAccepts;
    }

    public void setmFirstLevelAccepts(int mFirstLevelAccepts) {
        this.mFirstLevelAccepts = mFirstLevelAccepts;
    }

    public int getmNetworkLevelAccepts() {
        return mNetworkLevelAccepts;
    }

    public void setmNetworkLevelAccepts(int mNetworkLevelAccepts) {
        this.mNetworkLevelAccepts = mNetworkLevelAccepts;
    }
}
