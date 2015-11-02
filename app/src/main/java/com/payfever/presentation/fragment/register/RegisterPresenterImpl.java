package com.payfever.presentation.fragment.register;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.payfever.data.model.UserModel;
import com.payfever.domain.basics.BasePostGetInteractor;
import com.payfever.domain.interactors.registration.RegisterInteractor;
import com.payfever.presentation.PayFeverApplication;

import java.util.Locale;

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
        mView.initActionBar();
    }

    @Override
    public void onPause() {
        mView.hideProgress();
        mInteractor.unSubscribe();
    }

    @Override
    public void setView(RegisterView _view) {
        mView = _view;
    }

    @Override
    public void logIn() {
        mView.showLogIn();
    }

    @Override
    public void registerUser(UserModel _user) {
        if (!isUserValid(_user))
            return;

        _user.setPhoneNumber(formatNumber(_user.getPhoneNumber()));
        mView.showProgress();
        mInteractor.executePost(_user, new RegisterSubscriber());
    }

    private boolean isUserValid(UserModel _user) {
        boolean isValid = true;

        if (TextUtils.isEmpty(_user.getUserName().trim())) {
            mView.showUserNameError();
            isValid = false;
        }

        if (!isValidNumber(_user.getPhoneNumber())) {
            mView.showNumberError();
            isValid = false;
        }

        if (!isValidPassword(_user.getPassword())) {
            mView.showPasswordError();
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidNumber(String _number) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(_number, Locale.US.getCountry());
            return phoneNumberUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String formatNumber(String _phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(_phone, Locale.US.getCountry());
            return phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
            return "";
        }
    }

    private boolean isValidPassword(String _password) {
        boolean isValid = true;
        if (_password.length() < 4) {
            isValid = false;
        }

        return isValid;
    }

    private class RegisterSubscriber extends Subscriber<UserModel> {
        @Override
        public void onCompleted() {
            mView.hideProgress();
            mView.showTermsAndConditions();
        }

        @Override
        public void onError(Throwable e) {
            mView.hideProgress();
            mView.showServerError(e.getMessage());
        }

        @Override
        public void onNext(UserModel _userModel) {
            mUserModel = _userModel;
        }
    }
}
