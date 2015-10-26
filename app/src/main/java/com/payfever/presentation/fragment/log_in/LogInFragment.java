package com.payfever.presentation.fragment.log_in;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.model.LogInUserModel;
import com.payfever.presentation.activities.main.MainActivity;
import com.payfever.presentation.basics.BaseFragment;
import com.payfever.presentation.dialogs.AlertDialogManager;


/**
 * Created by richi on 2015.10.26..
 */
public class LogInFragment extends BaseFragment
        implements LogInView, View.OnClickListener {

    private TextView tvLogIn;
    private EditText etUserName, etPassword;

    private LogInPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    private LogInUserModel mData;

    private String mUserNameError, mPasswordError, mLogIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setContentView(R.layout.fragment_log_in);
        init();
    }

    private void init() {
        mData = new LogInUserModel();
        mPresenter = new LogInPresenterImpl();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initStrings();
        findUI();
        initListeners();

        mPresenter.attachView(this);
    }

    private void initStrings() {
        mUserNameError = mActivity.getString(R.string.user_name_error_FR);
        mPasswordError = mActivity.getString(R.string.password_error_FR);
        mLogIn         = mActivity.getString(R.string.log_in_process_FLI);
    }

    private void findUI() {
        tvLogIn     = $(R.id.tvLogIn_FLI);

        etPassword  = $(R.id.etPassword_FLI);
        etUserName  = $(R.id.etUserName_FLI);
    }

    private void initListeners() {
        tvLogIn.setOnClickListener(this);
    }


    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void initActionBar() {
        getToolbarController().setTitle(mActivity.getString(R.string.log_in_title_FLI));
        getToolbarController().showBackButton(mActivity);
    }

    @Override
    public void showPassWordError() {
        etPassword.setError(mPasswordError);
    }

    @Override
    public void showUserNameError() {
        etUserName.setError(mUserNameError);
    }

    @Override
    public void showLogInProgress() {
        mProgressDialog = AlertDialogManager.createProgressDialog(mActivity, mLogIn);
        mProgressDialog.show();
    }

    @Override
    public void hideLogInProgress() {
        mProgressDialog.cancel();
    }

    @Override
    public void showServerError(String _error) {
        Toast.makeText(mActivity.getApplicationContext(), _error, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void startMainActivity() {
        startActivity(MainActivity.getCallingIntent(mActivity));
        mActivity.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mActivity.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogIn_FLI:
                logInClicked();
                break;
        }
    }

    private void logInClicked() {
        mData.setPassword(etPassword.getText().toString());
        mData.setUserName(etUserName.getText().toString());
        mPresenter.logInClicked(mData);
    }
}
