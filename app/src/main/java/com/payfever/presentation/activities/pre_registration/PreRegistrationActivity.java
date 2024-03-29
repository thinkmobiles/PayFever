package com.payfever.presentation.activities.pre_registration;

import android.os.Bundle;
import android.view.MenuItem;

import com.payfever.R;
import com.payfever.presentation.activities.NetworkExceptionActivity;
import com.payfever.presentation.basics.BaseActivity;
import com.payfever.presentation.fragment.register.RegisterFragment;

/**
 * Created by richi on 2015.10.20..
 */
public class PreRegistrationActivity extends NetworkExceptionActivity implements PreRegistrationView {

    private PreRegistrationPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_registration);
        getLoadingManager().hideProgress();
        initPresenter();
        mPresenter.initialize(savedInstanceState);
    }

    @Override
    public int getNetworkExceptionLayoutId() {
        return 0;
    }

    private void initPresenter() {
        mPresenter = new PreRegistrationPresenterImpl();
        mPresenter.setView(this);
    }

    @Override
    public void showRegistrationFragment() {
        getFragmentNavigator().replaceFragment(new RegisterFragment());
    }

    @Override
    public int getProgressId() {
        return R.id.pbLoadingIndicator_APR;
    }

    @Override
    public int getToolbarId() {
        return R.id.toolbar_APR;
    }

    @Override
    public int getContainerId() {
        return R.id.flContainer_APR;
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
    public void setData(Void _data) {

    }
}
