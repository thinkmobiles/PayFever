package com.payfever.domain.basics;

import android.os.Handler;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by richi on 2015.10.19..
 */
public abstract class BaseInteractor {

    protected  Handler mHandler;
    protected Subscription mGetSubscription = Subscriptions.empty();

    public BaseInteractor() {}

    public BaseInteractor(Handler _handler) {
        mHandler = _handler;
    }

    @SuppressWarnings("unchecked")
    public void executeGET(Observer _subscriber) {
        mGetSubscription = buildGetObserver()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_subscriber);
    }

    public void unSubscribe() {
        if (!mGetSubscription.isUnsubscribed())
            mGetSubscription.unsubscribe();
    }

    protected abstract Observable buildGetObserver();
}
