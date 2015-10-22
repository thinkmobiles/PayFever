package com.payfever.presentation.activities.main;

import android.os.Bundle;

import com.payfever.R;
import com.payfever.presentation.basics.FABActivity;
import com.payfever.presentation.fragment.network.MyNetworkFragment;

/**
 * Created by richi on 2015.10.19..
 */
public class MainActivity extends FABActivity implements MainView {

    private MainPresenter mMainPresenter;

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
    public void showStatisticFragment() {
        getFragmentNavigator().replaceFragment(new MyNetworkFragment());
    }

    @Override
    public void showChartFragment() {

    }

    @Override
    public void showChatFragment() {

    }

    @Override
    public void showSetRingtoneFragment() {

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
}
