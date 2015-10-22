package com.payfever.data.api.network_api;

import com.parse.ParseException;
import com.payfever.data.model.network.NetworkResponse;
import com.payfever.data.model.network.NetworkUser;
import com.payfever.data.model.network.Statistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richi on 2015.10.22..
 */
public class NetworkTestApiImpl implements NetworkApi {

    @Override
    public NetworkResponse getNetworkStatistic() throws ParseException {
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return generateDummyData();
    }

    private NetworkResponse generateDummyData() {
        NetworkResponse networkResponse = new NetworkResponse();

        networkResponse.setmIsSucceeded(true);
        networkResponse.setmMessage("Test message");

        Statistic statistic = new Statistic();
        statistic.setmSentOut(15);
        statistic.setmPending(5);
        statistic.setmExpired(0);
        statistic.setmTotalNetworkAccepts(789);
        statistic.setmFirstLevelAccepts(10);
        statistic.setmNetworkLevelAccepts(779);
        statistic.setmUsers(getUserList());

        networkResponse.setmNetworkStatistic(statistic);

        return networkResponse;
    }

    private List<NetworkUser> getUserList() {
        List<NetworkUser> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            NetworkUser networkUser = new NetworkUser();
            networkUser.setUserName("Name: " + i);
            networkUser.setmIsFirstLevel((i % 3) == 0);
            networkUser.setmPushChannel("push channel: " + i);
            list.add(networkUser);
        }

        return list;
    }
}
