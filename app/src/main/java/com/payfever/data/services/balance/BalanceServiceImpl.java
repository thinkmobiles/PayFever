package com.payfever.data.services.balance;

import com.parse.ParseException;
import com.payfever.data.api.balance_api.BalanceApi;
import com.payfever.data.api.balance_api.BalanceApiImpl;
import com.payfever.data.exceptions.OnSubscribeWithNetworkCheck;
import com.payfever.data.model.balance.BalanceModel;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by richi on 2015.10.27..
 */
public class BalanceServiceImpl implements BalanceService {

    private BalanceApi mApi;

    public BalanceServiceImpl() {
        mApi = new BalanceApiImpl();
    }

    @Override
    public Observable<BalanceModel> getBalance() {
        return Observable.create(new OnSubscribeWithNetworkCheck<BalanceModel>() {
            @Override
            public void call(Subscriber<? super BalanceModel> subscriber) {
                super.call(subscriber);
                try {
                    subscriber.onNext(mApi.getBalance());
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
