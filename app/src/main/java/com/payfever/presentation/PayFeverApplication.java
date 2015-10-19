package com.payfever.presentation;

import android.app.Application;

/**
 * Created by richi on 2015.10.19..
 */
public class PayFeverApplication extends Application {

    private static PayFeverApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static PayFeverApplication getApplication() {
        return sInstance;
    }
}
