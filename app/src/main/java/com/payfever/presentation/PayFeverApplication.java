package com.payfever.presentation;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by richi on 2015.10.19..
 */
public class PayFeverApplication extends Application {

    private static PayFeverApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //TODO: initialize the Parse SDK
//        Parse.initialize();
    }

    public static PayFeverApplication getApplication() {
        return sInstance;
    }
}
