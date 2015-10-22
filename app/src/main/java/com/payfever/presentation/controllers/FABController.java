package com.payfever.presentation.controllers;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.payfever.presentation.basics.FABActivity;

/**
 * Created by richi on 2015.10.21..
 */
public final class FABController {

    private FloatingActionButton mFAB;

    public void register(FABActivity _activity) {
        mFAB = _activity.$(_activity.getFabId());
    }

    public void setOnClickListener(View.OnClickListener _listener) {
        mFAB.setOnClickListener(_listener);
    }

    public void show() {
        mFAB.show();
    }

    public void hide() {
        mFAB.hide();
    }
}
