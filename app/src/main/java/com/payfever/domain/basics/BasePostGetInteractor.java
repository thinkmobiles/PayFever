package com.payfever.domain.basics;

import android.os.Handler;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by richi on 2015.10.19..
 */
public abstract class BasePostGetInteractor<T> extends BaseInteractor {

    private Subscription mPostSubscription = Subscriptions.empty();

    public BasePostGetInteractor(Handler _handler) {
        super(_handler);
    }

    @SuppressWarnings("unchecked")
    public void executePost(T _data, Subscriber _subscriber) {
        mPostSubscription = buildPostObservable(_data)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_subscriber);
    }

    public abstract Observable buildPostObservable(T _data);

    @Override
    public void unSubscribe() {
        super.unSubscribe();
        if (!mPostSubscription.isUnsubscribed())
            mPostSubscription.unsubscribe();
    }
}
