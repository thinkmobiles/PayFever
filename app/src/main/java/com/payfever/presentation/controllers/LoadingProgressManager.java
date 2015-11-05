package com.payfever.presentation.controllers;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.exceptions.NetworkException;
import com.payfever.presentation.PayFeverApplication;
import com.payfever.presentation.activities.NetworkExceptionActivity;
import com.payfever.presentation.basics.BaseActivity;

/**
 * Created by richi on 2015.10.20..
 */
public final class LoadingProgressManager implements View.OnClickListener {

    private View mContainerView;
    private ProgressBar mProgressBar;
    private RelativeLayout rlNetworkException;
    private TextView tvRetry;
    private RetryListener retryListener;

    public void setRetryListener(RetryListener retryListener) {
        this.retryListener = retryListener;
    }

    public void register(NetworkExceptionActivity _baseActivity) {
        mContainerView = _baseActivity.$(_baseActivity.getContainerId());
        mProgressBar = _baseActivity.$(_baseActivity.getProgressId());
        rlNetworkException = _baseActivity.$(_baseActivity.getNetworkExceptionLayoutId());
        tvRetry = _baseActivity.$(R.id.tvRetry_NE);
        setListeners();
    }

    public void showProgress() {
        if (mProgressBar == null)
            return;

        mProgressBar.setVisibility(View.VISIBLE);
        mContainerView.setVisibility(View.GONE);
        rlNetworkException.setVisibility(View.GONE);
    }

    public void hideProgress() {
        if (mProgressBar == null)
            return;

        mProgressBar.setVisibility(View.GONE);
        mContainerView.setVisibility(View.VISIBLE);
        rlNetworkException.setVisibility(View.GONE);
    }

    public void showNetworkExceptionMessage(Throwable e) {
        if (e instanceof NetworkException) {
            rlNetworkException.setVisibility(View.VISIBLE);
            mContainerView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
        } else {
            Toast.makeText(PayFeverApplication.getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void retryInternetConnection() {
        rlNetworkException.setVisibility(View.GONE);
        showProgress();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRetry_NE:
                retryInternetConnection();
                if (retryListener != null)
                    retryListener.retryRequest();
                break;
        }
    }

    private void setListeners() {
        if (tvRetry != null)
        tvRetry.setOnClickListener(this);
    }

    public interface RetryListener {
        void retryRequest();
    }
}
