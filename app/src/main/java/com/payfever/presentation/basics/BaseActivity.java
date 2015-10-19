package com.payfever.presentation.basics;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.payfever.presentation.controllers.FragmentNavigator;

/**
 * Created by richi on 2015.10.15..
 */
public abstract class BaseActivity extends AppCompatActivity {

    private FragmentNavigator mFragmentNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentNavigator = new FragmentNavigator();
        registerFragmentNavigator();
    }

    private void registerFragmentNavigator() {
        mFragmentNavigator.register(getSupportFragmentManager(), getContainerId());
    }

    public FragmentNavigator getFragmentNavigator() {
        return mFragmentNavigator;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int _resId) {
        return (T) findViewById(_resId);
    }

    protected abstract @IdRes int getToolbarId();
    protected abstract @IdRes int getContainerId();
}
