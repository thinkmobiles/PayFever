package com.payfever.presentation.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;

import com.payfever.presentation.basics.BaseActivity;
import com.payfever.presentation.controllers.LoadingProgressManager;
import com.payfever.presentation.controllers.ToolbarController;

/**
 * Created by
 * mRogach on 05.11.2015.
 */

public abstract class NetworkExceptionActivity extends BaseActivity {

    private LoadingProgressManager mProgressManager;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mProgressManager = new LoadingProgressManager();
        mProgressManager.register(this);
    }
    public LoadingProgressManager getLoadingManager() {
        return mProgressManager;
    }
    public abstract @IdRes int getNetworkExceptionLayoutId();
}
