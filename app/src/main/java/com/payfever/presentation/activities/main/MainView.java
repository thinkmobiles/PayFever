package com.payfever.presentation.activities.main;


import com.payfever.presentation.basics.BaseView;

/**
 * Created by richi on 2015.10.19..
 */
public interface MainView extends BaseView<String> {
    void showStatisticFragment();
    void showChartFragment();
    void showChatFragment();
    void showSetRingtoneFragment();
    void showFAB();
    void hideFAB();
}
