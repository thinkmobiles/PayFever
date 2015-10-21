package com.payfever.domain.interactors.network;

import android.os.Handler;

import com.payfever.data.services.ServiceProvider;
import com.payfever.domain.basics.BaseInteractor;

import rx.Observable;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkInteractor extends BaseInteractor {

    public NetworkInteractor(Handler _handler) {
        super(_handler);
    }

    @Override
    public Observable buildGetObserver() {
        return ServiceProvider.getInstance().getNetworkService().getNetworkStatistic();
    }
}
