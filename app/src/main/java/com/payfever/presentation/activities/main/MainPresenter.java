package com.payfever.presentation.activities.main;

import android.view.View;

import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by richi on 2015.10.19..
 */
public interface MainPresenter extends BasePresenter<MainView> {
    void showNetWorkFragment();
    void showBalanceFragment();
    void showChatFragment();
    void showAboutFragment();
    void showSetRingtoneFragment();
    void onDrawerItemSelected(View view, int position);
}
