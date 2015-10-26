package com.payfever.presentation.fragment.register;

import com.payfever.data.model.UserModel;
import com.payfever.presentation.basics.BaseView;

/**
 * Created by richi on 2015.10.20..
 */
public interface RegisterView extends BaseView<UserModel> {
    void showUserNameError();
    void showNumberError();
    void showPasswordError();
    void showServerError(String _message);
    void initActionBar();
    void showTermsAndConditions();
    void showLogIn();
}
