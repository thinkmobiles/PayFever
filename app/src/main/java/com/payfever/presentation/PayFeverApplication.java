package com.payfever.presentation;

import android.app.Application;
import android.os.Handler;

import com.parse.Parse;

/**
 * Created by richi on 2015.10.19..
 */
public class PayFeverApplication extends Application {

    private static PayFeverApplication sInstance;

    private Handler backgroundHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());
        //TODO: initialize the Parse SDK
//        Parse.initialize();
    }

    public static PayFeverApplication getApplication() {
        return sInstance;
    }

    public Handler getBackgroundHandler() {
        return backgroundHandler;
    }
}
