package com.payfever.data.api.network_api;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by richi
 * on 2015.10.21..
 */

public class NetworkApiImpl implements NetworkApi {
    @Override
    public Map<String, Object> getNetworkStatistic() throws ParseException {
        Map<String, String> request = new HashMap<>();
        request.put("userId", ParseUser.getCurrentUser().getObjectId());
        Map<String, Object> response = ParseCloud.callFunction("get_referrals", request);

        return response;
    }


}
