package com.payfever.data.transformators.network;

import com.parse.ParseObject;
import com.payfever.data.model.network.NetworkUser;
import com.payfever.data.model.network.NetworkUserResponse;
import com.payfever.data.model.network.parse.NetworkParseWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by richi on 2015.11.02..
 */
public class NetworkTransfromatorImpl implements NetworkTransformator {

    @Override
    public NetworkUserResponse transform(Map<String, Object> _data) {
        List<NetworkUser> networkUsers = new ArrayList<>();
        NetworkUserResponse response = new NetworkUserResponse();
        List<Map<String, Object>> firstLevel = (List<Map<String, Object>>) _data.get("firstLevel");
        for (Map<String, Object> userMap : firstLevel) {
            NetworkUser networkUser = transform((NetworkParseWrapper) userMap.get("item"));
            if (networkUser != null) {
                networkUser.setUserName((String) userMap.get("userName"));
                networkUser.setmPushChannel((String) userMap.get("userName"));
                networkUser.setmIsFirstLevel(true);
                networkUsers.add(networkUser);
            }
        }

        int networkLevelCount = 0;
        List<List<Map<String, Object>>> networkLevel = (List<List<Map<String, Object>>>) _data.get("networkLevel");
        for (List<Map<String, Object>> networkLevelList : networkLevel) {
            for (Map<String, Object> userMap : networkLevelList) {
                NetworkUser networkUser = transform((NetworkParseWrapper) userMap.get("item"));
                if (networkUser != null) {
                    networkLevelCount++;
                    networkUser.setUserName((String) userMap.get("userName"));
                    networkUser.setmPushChannel((String) userMap.get("userName"));
                    networkUser.setmIsFirstLevel(false);
                    networkUsers.add(networkUser);
                }
            }
        }

        response.setmFirstLevel(firstLevel.size());
        response.setmNetworkLevel(networkLevelCount);

        response.setmUsers(networkUsers);

        return response;
    }

    @Override
    public NetworkUser transform(NetworkParseWrapper _parseObject) {
        if (!_parseObject.isAccepted())
            return null;
        NetworkUser networkUser = new NetworkUser();
        networkUser.setmPhoneNumber(_parseObject.getPhoneNumber());
        return networkUser;
    }
}
