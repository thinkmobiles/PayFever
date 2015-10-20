package com.payfever.presentation.controllers;

import android.view.View;
import android.widget.ProgressBar;

import com.payfever.presentation.basics.BaseActivity;

/**
 * Created by richi on 2015.10.20..
 */
public final class LoadingProgressManager {

    private View mContainerView;
    private ProgressBar mProgressBar;

    public void register(BaseActivity _baseActivity) {
        mContainerView = _baseActivity.$(_baseActivity.getContainerId());
        mProgressBar = _baseActivity.$(_baseActivity.getProgressId());
    }

    public void showProgress() {
        if (mProgressBar == null)
            return;

        mProgressBar.setVisibility(View.VISIBLE);
        mContainerView.setVisibility(View.GONE);
    }

    public void hideProgress() {
        if (mProgressBar == null)
            return;

        mProgressBar.setVisibility(View.GONE);
        mContainerView.setVisibility(View.VISIBLE);
    }
}
