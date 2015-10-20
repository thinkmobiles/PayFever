package com.payfever.data.services.registration;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.payfever.data.api.register_api.RegisterApImpl;
import com.payfever.data.api.register_api.RegisterApi;
import com.payfever.data.model.UserModel;


import rx.Observable;
import rx.Subscriber;

/**
 * Created by richi on 2015.10.20..
 */
public class RegisterServiceImpl implements RegisterService {

    private RegisterApi mRegisterApi;

    public RegisterServiceImpl() {
        mRegisterApi = new RegisterApImpl();
    }

    @Override
    public Observable<UserModel> register(final UserModel _userModel) {
        return Observable.create(new Observable.OnSubscribe<UserModel>() {
            @Override
            public void call(Subscriber<? super UserModel> subscriber) {
                try {
                    ParseUser user = mRegisterApi.register(_userModel);
                    _userModel.setPushChannel(user.getString("pushChannel"));
                    subscriber.onNext(_userModel);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
