package com.payfever.presentation.activities.main;

import android.os.Bundle;

/**
 * Created by richi on 2015.10.19..
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    @Override
    public void showStatisticFragment() {
        mMainView.showStatisticFragment();
    }

    @Override
    public void showChartFragment() {
        mMainView.showChartFragment();
    }

    @Override
    public void showChatFragment() {
        mMainView.showChatFragment();
    }

    @Override
    public void showSetRingtoneFragment() {
        mMainView.showSetRingtoneFragment();
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(MainView _view) {
        mMainView = _view;
    }
}
