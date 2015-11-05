package com.payfever.data.services.log_in;

import com.parse.ParseException;
import com.payfever.data.api.log_in.LogInApi;
import com.payfever.data.api.log_in.LogInApiImpl;
import com.payfever.data.exceptions.OnSubscribeWithNetworkCheck;
import com.payfever.data.model.LogInUserModel;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by richi on 2015.10.26..
 */
public class LogInServiceImpl implements LogInService {

    private LogInApi mApi;

    public LogInServiceImpl() {
        mApi = new LogInApiImpl();
    }

    @Override
    public Observable<LogInUserModel> logIn(final LogInUserModel _model) {
        return Observable.create(new OnSubscribeWithNetworkCheck<LogInUserModel>() {
            @Override
            public void call(Subscriber<? super LogInUserModel> _subscriber) {
                super.call(_subscriber);
                try {
                    mApi.logIn(_model);
                    _subscriber.onNext(_model);
                    _subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    _subscriber.onError(e);
                }
            }
        });
    }
}
