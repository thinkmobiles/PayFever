package com.payfever.presentation.fragment.log_in;

import com.payfever.data.model.LogInUserModel;

/**
 * Created by richi on 2015.10.26..
 */
public interface LogInPresenter {
    void onStart();
    void onStop();
    void attachView(LogInView _view);
    void logInClicked(LogInUserModel _user);
}
