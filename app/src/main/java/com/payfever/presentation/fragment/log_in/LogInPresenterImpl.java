package com.payfever.presentation.fragment.log_in;

import android.text.TextUtils;

import com.payfever.data.model.LogInUserModel;
import com.payfever.domain.interactors.log_in_interactor.LogInInteractor;

import rx.Observer;

/**
 * Created by richi on 2015.10.26..
 */
public class LogInPresenterImpl implements LogInPresenter {

    private LogInView mView;
    private LogInInteractor mInteractor;

    private LogInUserModel mModel;

    public LogInPresenterImpl() {
        mInteractor = new LogInInteractor();
    }

    @Override
    public void onStop() {
        mInteractor.unSubscribe();
    }

    @Override
    public void onStart() {
        mView.initActionBar();
    }

    @Override
    public void attachView(LogInView _view) {
        mView = _view;
    }

    @Override
    public void logInClicked(LogInUserModel _user) {
        if (isValidLogIn(_user))
            logIn(_user);

    }

    private boolean isValidLogIn(LogInUserModel _model) {
        boolean isValid = true;
        if (TextUtils.isEmpty(_model.getPassword().trim())) {
            isValid = false;
            mView.showPassWordError();
        } else if (TextUtils.isEmpty(_model.getUserName().trim())) {
            isValid = false;
            mView.showUserNameError();
        }

        return isValid;
    }

    private void logIn(LogInUserModel _model) {
        mView.showLogInProgress();
        mInteractor.executePost(_model, new LogInListener());
    }

    private class LogInListener implements Observer<LogInUserModel> {
        @Override
        public void onCompleted() {
            mView.hideLogInProgress();
            mView.startMainActivity();
        }

        @Override
        public void onError(Throwable e) {
            mView.hideLogInProgress();
            mView.showServerError(e.getMessage());
        }

        @Override
        public void onNext(LogInUserModel _logInUserModel) {
            mModel = _logInUserModel;
        }
    }
}
