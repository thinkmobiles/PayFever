package com.payfever.data.model.network;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkResponse {
    private boolean mIsSucceeded;
    private String mMessage;

    private Statistic mNetworkStatistic;

    public boolean ismIsSucceeded() {
        return mIsSucceeded;
    }

    public void setmIsSucceeded(boolean mIsSucceeded) {
        this.mIsSucceeded = mIsSucceeded;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public Statistic getmNetworkStatistic() {
        return mNetworkStatistic;
    }

    public void setmNetworkStatistic(Statistic mNetworkStatistic) {
        this.mNetworkStatistic = mNetworkStatistic;
    }
}
