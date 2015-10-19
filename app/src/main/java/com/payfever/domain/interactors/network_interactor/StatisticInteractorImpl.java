package com.payfever.domain.interactors.network_interactor;


import android.os.Handler;

import com.payfever.domain.basics.BaseInteractor;
import com.payfever.domain.services.ServiceProvider;

import rx.Observable;

/**
 * Created by richi on 2015.10.19..
 */
public class StatisticInteractorImpl extends BaseInteractor {

    public StatisticInteractorImpl(Handler _handler) {
        super(_handler);
    }

    @Override
    public Observable buildGetObserver() {
        return ServiceProvider.getInstance().getStatisticService().getStatisticData();
    }
}
