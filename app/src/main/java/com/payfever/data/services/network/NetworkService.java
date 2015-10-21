package com.payfever.data.services.network;

import com.payfever.data.model.network.NetworkResponse;

import rx.Observable;

/**
 * Created by richi on 2015.10.21..
 */
public interface NetworkService {
    Observable<NetworkResponse> getNetworkStatistic();
}
