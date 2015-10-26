package com.payfever.presentation.controllers;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.payfever.R;
import com.payfever.presentation.basics.BaseActivity;
import com.rey.material.widget.CheckBox;

/**
 * Created by richi on 2015.10.20..
 */
public final class ToolbarController {

    private Toolbar mToolbar;
    private LinearLayout llSelectAll;
    private CheckBox checkBox;


    public void register(BaseActivity _activity) {
        mToolbar = _activity.$(_activity.getToolbarId());
        if (mToolbar == null) return;
        _activity.setSupportActionBar(mToolbar);
        findViews();
    }

    public void showSelectAll() {
        llSelectAll.setVisibility(View.VISIBLE);
    }

    public void hideSelectAll() {
        llSelectAll.setVisibility(View.GONE);
        checkBox.setOnCheckedChangeListener(null);
    }

    public void setTitle(String _message) {
        mToolbar.setTitle(_message);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(@IdRes int _resId) {
        return (T) mToolbar.findViewById(_resId);
    }

    private void findViews() {
        llSelectAll = $(R.id.rlSelectAll_T);
    }

    public void onCheckBoxClick(final CompoundButton.OnCheckedChangeListener _listener) {
        checkBox = (CheckBox) llSelectAll.findViewById(R.id.chContactStatus_T);
        checkBox.setOnCheckedChangeListener(_listener);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void showBackButton(final BaseActivity _activity) {
        if (_activity.getSupportActionBar() == null)
            return;
        _activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        _activity.getSupportActionBar().setHomeButtonEnabled(true);
        _activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void hideBackBtn(final BaseActivity _activity) {
        if (_activity.getSupportActionBar() == null)
            return;
        _activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        _activity.getSupportActionBar().setHomeButtonEnabled(false);
        _activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
    }
}
