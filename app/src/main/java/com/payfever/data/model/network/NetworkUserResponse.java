package com.payfever.data.model.network;

import java.util.List;

/**
 * Created by richi on 2015.11.02..
 */
public class NetworkUserResponse {
    private int mFirstLevel;
    private int mNetworkLevel;

    private List<NetworkUser> mUsers;

    public int getmFirstLevel() {
        return mFirstLevel;
    }

    public void setmFirstLevel(int mFirstLevel) {
        this.mFirstLevel = mFirstLevel;
    }

    public int getmNetworkLevel() {
        return mNetworkLevel;
    }

    public void setmNetworkLevel(int mNetworkLevel) {
        this.mNetworkLevel = mNetworkLevel;
    }

    public List<NetworkUser> getmUsers() {
        return mUsers;
    }

    public void setmUsers(List<NetworkUser> mUsers) {
        this.mUsers = mUsers;
    }
}
