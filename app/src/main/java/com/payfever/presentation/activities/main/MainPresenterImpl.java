package com.payfever.presentation.activities.main;

import android.os.Bundle;
import android.view.View;

/**
 * Created by richi on 2015.10.19..
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    @Override
    public void showNetWorkFragment() {
        mMainView.showNetWorkFragment();
    }

    @Override
    public void showBalanceFragment() {
        mMainView.showBalanceFragment();
    }

    @Override
    public void showChatFragment() {
        mMainView.showChatFragment();
    }

    @Override
    public void showAboutFragment() {
        mMainView.showAboutFragment();
    }

    @Override
    public void showSetRingtoneFragment() {
        mMainView.showSetRingtoneFragment();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        mMainView.onMenuItemSelected(view, position);
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
        mMainView.showFAB();
        mMainView.setNavigationDrawerMenu();
    }

    @Override
    public void onPause() {
        mMainView.hideFAB();
    }

    @Override
    public void setView(MainView _view) {
        mMainView = _view;
    }
}
