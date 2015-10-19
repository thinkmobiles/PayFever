package com.payfever.domain.basics;

import android.os.Handler;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.subscriptions.Subscriptions;

/**
 * Created by richi on 2015.10.19..
 */
public abstract class BaseInteractor {

    protected final Handler mHandler;
    private Subscription mGetSubscription = Subscriptions.empty();

    public BaseInteractor(Handler _handler) {
        mHandler = _handler;
    }

    @SuppressWarnings("unchecked")
    private void executeGET(Subscriber _subscriber) {
        mGetSubscription = buildGetObserver()
                .subscribeOn(HandlerScheduler.from(mHandler))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_subscriber);
    }

    public void unSubscribe() {
        if (!mGetSubscription.isUnsubscribed())
            mGetSubscription.unsubscribe();
    }

    public abstract Observable buildGetObserver();
}
