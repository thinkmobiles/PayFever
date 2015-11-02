package com.payfever.data.services.network;

import com.payfever.data.model.network.NetworkUserResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by richi on 2015.11.02..
 */
public interface NetworkUserService {
    Observable<NetworkUserResponse> getNetworkUsers();
    Observable<Map<String, Object>> getNetworkResponse();
}
