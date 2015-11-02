package com.payfever.data.services.network;

import com.parse.ParseException;
import com.payfever.data.api.network_api.NetworkApi;
import com.payfever.data.api.network_api.NetworkApiImpl;
import com.payfever.data.model.network.NetworkUserResponse;
import com.payfever.data.transformators.network.NetworkTransformator;
import com.payfever.data.transformators.network.NetworkTransfromatorImpl;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by richi on 2015.11.02..
 */
public class NetworkUserSrviceImpl implements NetworkUserService {

    private NetworkApi mApi;
    private NetworkTransformator mTransformator;

    public NetworkUserSrviceImpl() {
        mApi = new NetworkApiImpl();
        mTransformator = new NetworkTransfromatorImpl();
    }

    @Override
    public Observable<NetworkUserResponse> getNetworkUsers() {
        return getNetworkResponse()
                .map(new Func1<Map<String, Object>, NetworkUserResponse>() {
                    @Override
                    public NetworkUserResponse call(Map<String, Object> stringListMap) {
                        return mTransformator.transform(stringListMap);
                    }
                });
    }

    @Override
    public Observable<Map<String, Object>> getNetworkResponse() {
        return Observable.create(new Observable.OnSubscribe<Map<String, Object>>() {
            @Override
            public void call(Subscriber<? super Map<String, Object>> subscriber) {
                try {
                    subscriber.onNext(mApi.getNetworkStatistic());
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }
}
