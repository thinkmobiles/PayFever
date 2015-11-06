package com.payfever.presentation.basics;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payfever.presentation.activities.NetworkExceptionActivity;
import com.payfever.presentation.controllers.FABController;
import com.payfever.presentation.controllers.FragmentNavigator;
import com.payfever.presentation.controllers.LoadingProgressManager;
import com.payfever.presentation.controllers.ToolbarController;

/**
 * Created by richi on 2015.10.15..
 */
public abstract class BaseFragment extends Fragment implements LoadingProgressManager.RetryListener {

    private int mLayoutRes = -1;
    private View mRootView;

    private FragmentNavigator mFragmentNavigator;
    private LoadingProgressManager mLoadingManager;
    private ToolbarController mToolbarController;
    private FABController mFabController;

    protected NetworkExceptionActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mLayoutRes == -1)
            throw new RuntimeException("You have to call setContentView!");

        mRootView = inflater.inflate(mLayoutRes, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (NetworkExceptionActivity) getActivity();
        mFragmentNavigator = mActivity.getFragmentNavigator();
        mLoadingManager = mActivity.getLoadingManager();
        mToolbarController = mActivity.getToolbarController();
        setListeners();
    }

    private void setListeners() {
        mLoadingManager.setRetryListener(this);
    }

    protected FragmentNavigator getFargmentNavigator() {
        return mFragmentNavigator;
    }

    protected LoadingProgressManager getLoadingManager() {
        return mLoadingManager;
    }

    protected ToolbarController getToolbarController() {
        return mToolbarController;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int _resId) {
        return (T) mRootView.findViewById(_resId);
    }

    protected void setContentView(@LayoutRes int _resLayout) {
        mLayoutRes = _resLayout;
    }

    @Override
    public void retryRequest() {
    }
}
