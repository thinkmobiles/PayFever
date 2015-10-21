package com.payfever.data.services.registration;

import com.payfever.data.model.UserModel;

import rx.Observable;

/**
 * Created by richi on 2015.10.20..
 */
public interface RegisterService {
    Observable<UserModel> register(UserModel _userModel);
}
