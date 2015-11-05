package com.payfever.presentation;

import android.app.Application;
import android.os.Handler;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by richi on 2015.10.19..
 */
public class PayFeverApplication extends Application {

    private static final String CLIENT_KEY = "vljKH0MqIUVkND60Bk1R8Xj8eob1cW9jrw2FEKXk";
    private static final String APPLICATION_ID = "6QqstrJ9wIguSmW7FdUGcvbV82Iu8G43LHVj8a6D";


    private static PayFeverApplication sInstance;
    private Handler backgroundHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
    }

    public static PayFeverApplication getApplication() {
        return sInstance;
    }

    public Handler getBackgroundHandler() {
        return backgroundHandler;
    }
}
