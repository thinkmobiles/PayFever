package com.payfever.presentation.activities.contact;

import android.os.Bundle;

import com.payfever.presentation.basics.BaseView;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactView extends BaseView {

    void getContactList();
    void invite();
    void initialize(Bundle _savedInstanceState);

}
