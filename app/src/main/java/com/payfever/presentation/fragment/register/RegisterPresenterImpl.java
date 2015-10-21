package com.payfever.presentation.fragment.register;

import android.os.Bundle;
import android.text.TextUtils;

import com.payfever.data.model.UserModel;
import com.payfever.domain.basics.BasePostGetInteractor;
import com.payfever.domain.interactors.registration.RegisterInteractor;
import com.payfever.presentation.PayFeverApplication;

import rx.Subscriber;

/**
 * Created by richi on 2015.10.20..
 */
public class RegisterPresenterImpl implements RegisterPresenter {

    private UserModel mUserModel;

    private RegisterView mView;
    private BasePostGetInteractor<UserModel> mInteractor;

    @Override
    public void initialize(Bundle _savedInstanceState) {
        mInteractor = new RegisterInteractor(PayFeverApplication.getApplication().getBackgroundHandler());
        mView.setTitle();
    }

    @Override
    public void onPause() {
        mInteractor.unSubscribe();
    }

    @Override
    public void setView(RegisterView _view) {
        mView = _view;
    }

    @Override
    public void registerUser(UserModel _user) {
        if (!isUserValid(_user))
            return;

        mInteractor.executePost(_user, new RegisterSubscriber());
    }

    private boolean isUserValid(UserModel _user) {
        boolean isValid = true;

        if (TextUtils.isEmpty(_user.getUserName().trim())) {
            mView.showUserNameError();
            isValid = false;
        }

        if (TextUtils.isEmpty(_user.getPhoneNumber().trim())) {
            mView.showNumberError();
            isValid = false;
        }

        return isValid;
    }

    private class RegisterSubscriber extends Subscriber<UserModel> {
        @Override
        public void onCompleted() {
            mView.showServerError("Success! :D");
        }

        @Override
        public void onError(Throwable e) {
            mView.showServerError(e.getMessage());
        }

        @Override
        public void onNext(UserModel _userModel) {
            mUserModel = _userModel;
        }
    }
}
