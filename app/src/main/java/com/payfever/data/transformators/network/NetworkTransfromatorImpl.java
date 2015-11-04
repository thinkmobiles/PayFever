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
            networkUser.setmIsFirstLevel(true);
            if (networkUser != null)
                networkUsers.add(networkUser);
        }

        response.setmUsers(networkUsers);

        return response;
    }

    @Override
    public NetworkUser transform(NetworkParseWrapper _parseObject) {
        if (!_parseObject.isAccepted())
            return null;
        NetworkUser networkUser = new NetworkUser();
        networkUser.setUserName(_parseObject.getUserName());
        networkUser.setmPushChannel(_parseObject.getPushChannel());
        networkUser.setmPhoneNumber(_parseObject.getPhoneNumber());
        return networkUser;
    }
}
