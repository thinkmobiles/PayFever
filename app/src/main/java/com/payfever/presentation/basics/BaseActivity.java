package com.payfever.presentation.basics;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.payfever.presentation.controllers.FragmentNavigator;
import com.payfever.presentation.controllers.LoadingProgressManager;
import com.payfever.presentation.controllers.ToolbarController;

/**
 * Created by richi on 2015.10.15..
 */
public abstract class BaseActivity extends AppCompatActivity {

    private FragmentNavigator mFragmentNavigator;
    private LoadingProgressManager mProgressManager;
    private ToolbarController mToolbarController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentNavigator = new FragmentNavigator();
        registerControllers();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mProgressManager = new LoadingProgressManager();
        mProgressManager.register(this);

        mToolbarController = new ToolbarController();
        mToolbarController.register(this);
    }

    private void registerControllers() {
        mFragmentNavigator.register(getSupportFragmentManager(), getContainerId());
    }

    public FragmentNavigator getFragmentNavigator() {
        return mFragmentNavigator;
    }

    public LoadingProgressManager getLoadingManager() {
        return mProgressManager;
    }

    public ToolbarController getToolbarController() {
        return mToolbarController;
    }

    @SuppressWarnings("unchecked")
    public  <T extends View> T $(@IdRes int _resId) {
        return (T) findViewById(_resId);
    }

    public abstract @IdRes int getToolbarId();
    public abstract @IdRes int getContainerId();
    public abstract @IdRes int getProgressId();
}
