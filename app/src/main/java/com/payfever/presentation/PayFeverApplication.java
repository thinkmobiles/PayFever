package com.payfever.presentation;

import android.app.Application;
import android.os.Handler;

import com.parse.Parse;

/**
 * Created by richi on 2015.10.19..
 */
public class PayFeverApplication extends Application {

    private static final String CLIENT_KEY = "9lyw6rDdicYkMmvlTySqrNNREJJl9lGmbM5efQZy";
    private static final String APPLICATION_ID = "0PQAVB8jydFiqcqABb9m9iY2N2l3QsGhK1dmUS7k";


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
