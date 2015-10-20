package com.payfever.presentation.activities.pre_registration;

import android.os.Bundle;

/**
 * Created by richi on 2015.10.20..
 */
public class PreRegistrationPresenterImpl implements PreRegistrationPresenter {

    private PreRegistrationView mView;

    @Override
    public void initialize(Bundle _savedInstanceState) {
        if (_savedInstanceState == null) {
            mView.showRegistrationFragment();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(PreRegistrationView _view) {
        mView = _view;
    }
}
