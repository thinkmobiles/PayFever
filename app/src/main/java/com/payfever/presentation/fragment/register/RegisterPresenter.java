package com.payfever.presentation.fragment.register;

import com.payfever.data.model.UserModel;
import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by richi on 2015.10.20..
 */
public interface RegisterPresenter extends BasePresenter<RegisterView> {
    void registerUser(UserModel _user);
    void logIn();
    void onResume();
}
