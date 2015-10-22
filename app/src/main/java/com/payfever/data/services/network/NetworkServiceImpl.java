package com.payfever.data.services.network;

import com.parse.ParseException;
import com.payfever.data.api.network_api.NetworkApi;
import com.payfever.data.api.network_api.NetworkTestApiImpl;
import com.payfever.data.model.network.NetworkResponse;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkServiceImpl implements NetworkService {

    private NetworkApi mApi;

    public NetworkServiceImpl() {
        //Real
//        mApi = new NetworkApiImpl();

        //Test
        mApi = new NetworkTestApiImpl();
    }

    @Override
    public Observable<NetworkResponse> getNetworkStatistic() {
        return Observable.create(new Observable.OnSubscribe<NetworkResponse>() {
            @Override
            public void call(Subscriber<? super NetworkResponse> subscriber) {
                try {
                    NetworkResponse data = mApi.getNetworkStatistic();
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
