package com.payfever.presentation.fragment.terms_and_conditions;

import android.os.Bundle;

/**
 * Created by
 * mRogach on 21.10.2015.
 */

public interface TermsConditionsPresenter {

    void initialize(Bundle _savedInstanceState);
    void onPause();
    void setView(TermsConditionsView _view);
    void accept();
}
