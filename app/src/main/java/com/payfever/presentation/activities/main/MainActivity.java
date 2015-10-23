package com.payfever.presentation.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.payfever.R;
import com.payfever.presentation.activities.main.navigation_drawer.FragmentDrawerListener;
import com.payfever.presentation.activities.main.navigation_drawer.FragmentDrawerMenu;
import com.payfever.presentation.basics.FABActivity;
import com.payfever.presentation.fragment.network.MyNetworkFragment;

/**
 * Created by richi on 2015.10.19..
 */
public class MainActivity extends FABActivity implements MainView, FragmentDrawerListener {

    private MainPresenter mMainPresenter;
    private FragmentDrawerMenu drawerFragment;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();
        initListeners();

        mMainPresenter.initialize(savedInstanceState);
    }

    private void initPresenter() {
        mMainPresenter = new MainPresenterImpl();
        mMainPresenter.setView(this);
    }

    private void initListeners() {

    }

    @Override
    public void showProgress() {
        getLoadingManager().showProgress();
    }

    @Override
    public void hideProgress() {
        getLoadingManager().hideProgress();
    }

    @Override
    public void setData(String _data) {

    }

    @Override
    public void showNetWorkFragment() {
        getFragmentNavigator().replaceFragment(new MyNetworkFragment());
    }

    @Override
    public void showBalanceFragment() {
        getFragmentNavigator().replaceFragment(new MyNetworkFragment());
    }

    @Override
    public void showChatFragment() {
        getFragmentNavigator().replaceFragment(new MyNetworkFragment());
    }

    @Override
    public void showSetRingtoneFragment() {
        getFragmentNavigator().replaceFragment(new MyNetworkFragment());
    }

    @Override
    public void showAboutFragment() {
        getFragmentNavigator().replaceFragment(new MyNetworkFragment());
    }

    @Override
    public void showFAB() {
        getFABController().show();
    }

    @Override
    public void hideFAB() {
        getFABController().hide();
    }

    @Override
    public void setNavigationDrawerMenu() {
        drawerFragment = (FragmentDrawerMenu)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(MainActivity.this, R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), getToolbarController().getToolbar());
        drawerFragment.setDrawerListener(this);
        displayView(0);
    }

    @Override
    public void onMenuItemSelected(View view, int position) {
        displayView(position);
    }

    @Override
    public int getToolbarId() {
        return R.id.toolbar_AM;
    }

    @Override
    public int getContainerId() {
        return R.id.flContainer_AM;
    }

    @Override
    public int getProgressId() {
        return R.id.pbDownload_AM;
    }

    @Override
    public int getFabId() {
        return R.id.fab_AM;
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        mMainPresenter.onDrawerItemSelected(view, position);
    }

    private void displayView(int position) {
        switch (position) {
            case 0:
                showNetWorkFragment();
                break;
            case 1:
                showBalanceFragment();
                break;
            case 2:
                showChatFragment();
                break;
            case 3:
                showAboutFragment();
                break;
            case 4:
                showSetRingtoneFragment();
                break;
            default:
                break;
        }
    }
}
