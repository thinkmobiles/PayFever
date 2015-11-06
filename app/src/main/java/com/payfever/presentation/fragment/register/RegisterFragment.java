package com.payfever.presentation.fragment.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.model.UserModel;
import com.payfever.presentation.basics.BaseFragment;
import com.payfever.presentation.dialogs.AlertDialogManager;
import com.payfever.presentation.fragment.log_in.LogInFragment;
import com.payfever.presentation.fragment.terms_and_conditions.TermsConditionsFragment;

/**
 * Created by richi on 2015.10.20..
 */
public class RegisterFragment extends BaseFragment
        implements RegisterView, View.OnClickListener {

    private EditText etUserName, etPhoneNumber, etPassword;
    private TextView tvRegister, tvLogIn;
    private ProgressDialog mProgressDialog;

    private RegisterPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initProgressDialog();
        initPresenter();
        findUI();
        initListeners();

        mPresenter.initialize(savedInstanceState);
    }

    private void initPresenter() {
        mPresenter = new RegisterPresenterImpl();
        mPresenter.setView(this);
    }

    private void initProgressDialog() {
        mProgressDialog = AlertDialogManager.createProgressDialog(mActivity, "Registration...");
    }

    private void findUI() {
        etPhoneNumber       = $(R.id.etPhoneNumber_FR);
        etUserName          = $(R.id.etUserName_FR);
        etPassword          = $(R.id.etPassword_FR);

        tvRegister          = $(R.id.tvRegister_FR);
        tvLogIn             = $(R.id.tvLogIn_FR);
    }

    private void initListeners() {
        tvRegister.setOnClickListener(this);
        tvLogIn.setOnClickListener(this);
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.cancel();
    }

    @Override
    public void setData(UserModel _data) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void showUserNameError() {
        etUserName.setError(mActivity.getString(R.string.user_name_error_FR));
    }

    @Override
    public void showPasswordError() {
        etPassword.setError(mActivity.getString(R.string.password_error_FR));
    }

    @Override
    public void showNumberError() {
        etPhoneNumber.setError(mActivity.getString(R.string.number_error_FR));
    }

    @Override
    public void initActionBar() {
        getToolbarController().setTitleCenter(getString(R.string.register_FR));
        getToolbarController().hideBackBtn(mActivity);
    }

    @Override
    public void showTermsAndConditions() {
        mActivity.getFragmentNavigator().showFragment(new TermsConditionsFragment());
    }

    @Override
    public void showServerError(String _message) {
        Toast.makeText(mActivity, _message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRegister_FR:
                mPresenter.registerUser(new UserModel(etUserName.getText().toString(),
                        etPhoneNumber.getText().toString(), etPassword.getText().toString()));
                break;
            case R.id.tvLogIn_FR:
                mPresenter.logIn();
                break;
        }
    }

    @Override
    public void showLogIn() {
        getFargmentNavigator().showFragment(new LogInFragment());
    }
}
