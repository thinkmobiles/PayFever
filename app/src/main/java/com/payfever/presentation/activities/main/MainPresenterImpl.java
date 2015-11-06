package com.payfever.presentation.activities.main;

import android.os.Bundle;
import android.view.View;

import com.payfever.presentation.fragment.about.AboutUsFragment;
import com.payfever.presentation.fragment.balance.BalanceFragment;
import com.payfever.presentation.fragment.chat_list.ChatListFragment;
import com.payfever.presentation.fragment.network.MyNetworkFragment;
import com.payfever.presentation.fragment.set_ringtones.RingtonesFragment;

/**
 * Created by richi on 2015.10.19..
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    @Override
    public void showNetWorkFragment() {
        if (mMainView.getCurrentFragment() instanceof MyNetworkFragment)
            mMainView.closeMenu();
        else
            mMainView.showNetWorkFragment();
    }

    @Override
    public void showBalanceFragment() {
        if (mMainView.getCurrentFragment() instanceof BalanceFragment)
            mMainView.closeMenu();
        else
            mMainView.showBalanceFragment();
    }

    @Override
    public void showChatFragment() {
        if (mMainView.getCurrentFragment() instanceof ChatListFragment)
            mMainView.closeMenu();
        else
            mMainView.showChatFragment();
    }

    @Override
    public void showAboutFragment() {
        if (mMainView.getCurrentFragment() instanceof AboutUsFragment)
            mMainView.closeMenu();
        else
            mMainView.showAboutFragment();
    }

    @Override
    public void showSetRingtoneFragment() {
        if (mMainView.getCurrentFragment() instanceof RingtonesFragment)
            mMainView.closeMenu();
        else
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
