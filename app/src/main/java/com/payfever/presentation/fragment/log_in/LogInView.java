package com.payfever.presentation.fragment.log_in;

/**
 * Created by richi on 2015.10.26..
 */
public interface LogInView {
    void showLogInProgress();
    void hideLogInProgress();
    void showPassWordError();
    void showUserNameError();
    void showServerError(String _error);
    void startMainActivity();
    void initActionBar();
}
