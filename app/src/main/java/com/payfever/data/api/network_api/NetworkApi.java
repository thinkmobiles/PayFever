package com.payfever.data.api.network_api;

import com.parse.ParseException;
import java.util.Map;

/**
 * Created by richi on 2015.10.21..
 */
public interface NetworkApi {
    Map<String, Object> getNetworkStatistic() throws ParseException;
}
