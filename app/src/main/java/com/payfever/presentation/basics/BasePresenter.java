package com.payfever.presentation.basics;

import android.os.Bundle;

/**
 * Created by richi on 2015.10.19..
 */
public interface BasePresenter<V extends BaseView> {
    void initialize(Bundle _savedInstanceState);
    void onPause();
    void showProgress();
    void hideProgress();
    void setView(V _view);
}
