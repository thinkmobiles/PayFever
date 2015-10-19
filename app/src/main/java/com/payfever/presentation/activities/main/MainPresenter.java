package com.payfever.presentation.activities.main;

import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by richi on 2015.10.19..
 */
public interface MainPresenter extends BasePresenter<MainView> {
    void showStatisticFragment();
    void showChartFragment();
    void showChatFragment();
    void showSetRingtoneFragment();
}
