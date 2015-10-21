package com.payfever.presentation.activities;

import android.content.Intent;
import android.os.Bundle;

import com.payfever.R;
import com.payfever.presentation.activities.contact.ContactActivity;
import com.payfever.presentation.activities.pre_registration.PreRegistrationActivity;
import com.payfever.presentation.basics.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(SplashActivity.this, PreRegistrationActivity.class);
        startActivity(intent);
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
