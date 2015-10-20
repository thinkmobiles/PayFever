package com.payfever.domain.interactors.registration;

import android.os.Handler;

import com.payfever.data.model.UserModel;
import com.payfever.data.services.ServiceProvider;
import com.payfever.domain.basics.BasePostGetInteractor;

import rx.Observable;

/**
 * Created by richi on 2015.10.20..
 */
public class RegisterInteractor extends BasePostGetInteractor<UserModel> {

    public RegisterInteractor(Handler _handler) {
        super(_handler);
    }

    @Override
    public Observable buildPostObservable(UserModel _data) {
        return ServiceProvider.getInstance().getRegisterService().register(_data);
    }

    @Override
    public Observable buildGetObserver() {
        throw new IllegalArgumentException("This operation is not supported here");
    }
}
