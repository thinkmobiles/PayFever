package com.payfever.presentation.controllers;

import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.payfever.presentation.basics.BaseActivity;

/**
 * Created by richi on 2015.10.20..
 */
public final class ToolbarController {

    private Toolbar mToolbar;

    public void register(BaseActivity _activity) {
        mToolbar = _activity.$(_activity.getToolbarId());
    }

    public void showSelectAll() {

    }

    public void hideSelectAll() {

    }

    public void setTitle(String _message) {
        mToolbar.setTitle(_message);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(@IdRes int _resId) {
        return (T) mToolbar.findViewById(_resId);
    }
}
