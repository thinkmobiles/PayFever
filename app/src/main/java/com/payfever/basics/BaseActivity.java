package com.payfever.basics;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by richi on 2015.10.15..
 */
public abstract class BaseActivity extends AppCompatActivity {

    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int _resId) {
        return (T) findViewById(_resId);
    }
}
