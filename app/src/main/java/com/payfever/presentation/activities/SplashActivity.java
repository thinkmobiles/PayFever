package com.payfever.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parse.ParseUser;
import com.payfever.R;
import com.payfever.presentation.activities.contact.ContactActivity;
import com.payfever.presentation.activities.main.MainActivity;
import com.payfever.presentation.activities.pre_registration.PreRegistrationActivity;
import com.payfever.presentation.basics.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static final int DELAY = 2000;

    private Handler mHandler;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            startActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, DELAY);
    }

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(mRunnable);
        super.onPause();
    }

    private void startActivity() {
        Intent intent;
        if (ParseUser.getCurrentUser() == null) {
            intent = new Intent(SplashActivity.this, PreRegistrationActivity.class);
        } else {
            intent = new Intent(this, ContactActivity.class);
        }

        startActivity(intent);
        finish();
    }

    @Override
    public int getProgressId() {
        return 0;
    }

    @Override
    public int getToolbarId() {
        return 0;
    }

    @Override
    public int getContainerId() {
        return 0;
    }
}
