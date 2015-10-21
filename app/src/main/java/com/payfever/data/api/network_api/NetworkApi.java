package com.payfever.data.api.network_api;

import com.parse.ParseException;
import com.payfever.data.model.network.NetworkResponse;

/**
 * Created by richi on 2015.10.21..
 */
public interface NetworkApi {
    NetworkResponse getNetworkStatistic() throws ParseException;
}
