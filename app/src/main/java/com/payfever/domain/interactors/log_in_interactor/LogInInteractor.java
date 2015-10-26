package com.payfever.domain.interactors.log_in_interactor;

import com.payfever.data.model.LogInUserModel;
import com.payfever.data.services.ServiceProvider;
import com.payfever.domain.basics.BasePostGetInteractor;

import rx.Observable;

/**
 * Created by richi on 2015.10.26..
 */
public class LogInInteractor extends BasePostGetInteractor<LogInUserModel> {

    @Override
    public Observable buildPostObservable(LogInUserModel _data) {
        return ServiceProvider.getInstance().getLogInService().logIn(_data);
    }

    @Override
    protected Observable buildGetObserver() {
        throw new UnsupportedOperationException("This method is not supported in login");
    }
}
