package com.payfever.data.transformators.network;

import com.parse.ParseObject;
import com.payfever.data.model.network.NetworkUserResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by richi on 2015.11.02..
 */
public class NetworkTransfromatorImpl implements NetworkTransformator {
    @Override
    public NetworkUserResponse transform(Map<String, Object> _data) {
        NetworkUserResponse response = new NetworkUserResponse();
//        List<ParseObject> firstLevel = _data.get("firstLevel");
//        List<ParseObject> networkLevel = _data.get("networkLevel");

        return response;
    }
}
