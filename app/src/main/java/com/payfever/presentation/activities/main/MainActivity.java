package com.payfever.presentation.activities.main;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.payfever.R;
import com.payfever.data.model.StatisticModel;
import com.payfever.presentation.basics.BaseActivity;

/**
 * Created by richi on 2015.10.19..
 */
public class MainActivity extends BaseActivity implements MainView, RadioGroup.OnCheckedChangeListener {

    private MainPresenter mMainPresenter;
    private RadioGroup rgContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();
        findUI();
        initListeners();
    }

    private void initPresenter() {
        mMainPresenter = new MainPresenterImpl();
        mMainPresenter.setView(this);
    }

    private void findUI() {
        rgContainer = $(R.id.rgContainer_AM);
    }

    private void initListeners() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setData(StatisticModel _data) {

    }

    @Override
    public void showStatisticFragment() {

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
    public int getToolbarId() {
        return R.id.toolbar_AM;
    }

    @Override
    public int getContainerId() {
        return R.id.flContainer_AM;
    }

    @Override
    public int getProgressId() {
        return 0;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbStatistic_AM:
                mMainPresenter.showStatisticFragment();
                break;
            case R.id.rbChartView_AM:
                mMainPresenter.showChartFragment();
                break;
            case R.id.rbChat_AM:
                mMainPresenter.showChatFragment();
                break;
            default:
                mMainPresenter.showSetRingtoneFragment();
        }
    }
}
