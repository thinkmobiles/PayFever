package com.payfever.data.exceptions;

import com.payfever.data.utils.NetWorkManager;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by
 * mRogach on 05.11.2015.
 */

public class OnSubscribeWithNetworkCheck<T> implements Observable.OnSubscribe<T> {

    @Override
    public void call(Subscriber<? super T> subscriber) {
        if (!NetWorkManager.isNetworkAvailable())
            subscriber.onError(new NetworkException());
    }
}
