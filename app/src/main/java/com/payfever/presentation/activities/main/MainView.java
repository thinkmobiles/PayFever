package com.payfever.presentation.activities.main;


import android.view.View;

import com.payfever.presentation.basics.BaseView;

/**
 * Created by richi on 2015.10.19..
 */
public interface MainView extends BaseView<String> {
    void showNetWorkFragment();
    void showBalanceFragment();
    void showChatFragment();
    void showSetRingtoneFragment();
    void showAboutFragment();
    void showFAB();
    void hideFAB();
    void setNavigationDrawerMenu();
    void onMenuItemSelected(View view, int position);
}
