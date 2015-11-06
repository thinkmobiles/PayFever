package com.payfever.presentation.fragment.terms_and_conditions;

import android.os.Bundle;

/**
 * Created by
 * mRogach on 21.10.2015.
 */

public final class TermsConditionsPresenterImpl implements TermsConditionsPresenter {

    private TermsConditionsView mView;

    @Override
    public void initialize(Bundle _savedInstanceState) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(TermsConditionsView _view) {
        mView = _view;
        mView.setTitle();
    }

    @Override
    public void accept() {
        mView.accept();
    }
}
