package com.payfever.domain.interactors;


import android.os.Handler;

import com.payfever.domain.BaseInteractor;

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
        return null;
    }
}
