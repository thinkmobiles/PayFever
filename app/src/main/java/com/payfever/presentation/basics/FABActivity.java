package com.payfever.presentation.basics;

import android.support.annotation.IdRes;

import com.payfever.presentation.controllers.FABController;

/**
 * Created by richi on 2015.10.21..
 */
public abstract class FABActivity extends BaseActivity {

    private FABController mFabController;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mFabController = new FABController();
        mFabController.register(this);
    }

    public FABController getFABController() {
        return mFabController;
    }

    protected abstract @IdRes int getFabId();
}
