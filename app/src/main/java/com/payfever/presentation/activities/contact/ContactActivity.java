package com.payfever.presentation.activities.contact;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.model.ContactModel;
import com.payfever.presentation.activities.NetworkExceptionActivity;
import com.payfever.presentation.activities.main.MainActivity;
import com.payfever.presentation.basics.BaseActivity;
import com.payfever.presentation.controllers.LoadingProgressManager;
import com.payfever.presentation.dialogs.AlertDialogManager;
import com.payfever.presentation.dialogs.AlertDialogModel;
import com.payfever.presentation.dialogs.TwoButtonDialogListener;
import com.payfever.presentation.global.Constants;
import com.rey.material.widget.CheckBox;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public class ContactActivity extends NetworkExceptionActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener,
        ContactView, CompoundButton.OnCheckedChangeListener, LoadingProgressManager.RetryListener {

    private ListView listView;
    private TextView tvInvite;
    private TextView tvEmptyList;
    private TextView tvSkip;
    private ContactPresenter mContactsPresenter;
    private ContactListAdapter mContactListAdapter;
    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog mProgressDialog;


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ContactActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        findViews();
        initToolbarController();
        initObjects();
        setListeners();
        mContactsPresenter.setView(this);
        mContactsPresenter.setExtra(getIntent());
        mContactsPresenter.initialize(savedInstanceState);
    }

    @Override
    public int getNetworkExceptionLayoutId() {
        return R.id.rlNetworkConnectionError_AC;
    }

    private void initToolbarController() {
        getToolbarController().showSelectAll();
        getToolbarController().onCheckBoxClick(this);
        getToolbarController().setSelectAllEnabled(false);
        getToolbarController().setTitle("Import Contacts");
    }

    @Override
    public void hideSkipShowBack() {
        setSupportActionBar(getToolbarController().getToolbar());
        getToolbarController().showBackButton(this);
        tvSkip.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSkip_AC:
                mContactsPresenter.skip();
                break;
            case R.id.tvInvite_AC:
                mContactsPresenter.invite();
                break;
        }
    }

    private void findViews() {
        listView            = $(R.id.lvContacts_AC);
        tvSkip              = $(R.id.tvSkip_AC);
        tvInvite            = $(R.id.tvInvite_AC);
        tvEmptyList         = $(R.id.tvEmptyList_AC);
        coordinatorLayout   = $(R.id.coordinatorLayout);
    }

    private void initObjects() {
        mContactsPresenter = new ContactPresenterImpl();
        mContactListAdapter = new ContactListAdapter();
    }

    private void setListeners() {
        tvSkip.setOnClickListener(this);
        tvInvite.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        getLoadingManager().setRetryListener(this);
    }

    @Override
    public void showProgress() {
        getLoadingManager().showProgress();
    }

    @Override
    public void hideProgress() {
        getLoadingManager().hideProgress();
    }

    @Override
    public void startMainActivity() {
        startActivity(MainActivity.getCallingIntent(ContactActivity.this));
        finish();
    }

    @Override
    public void onBack() {
        finish();
    }

    @Override
    public void showInviteProgress() {
        mProgressDialog = AlertDialogManager.createProgressDialog(this, "Invite...");
        mProgressDialog.show();
    }

    @Override
    public void hideInviteProgress() {
        mProgressDialog.cancel();
    }

    @Override
    public void showServerError(Throwable e) {
        getLoadingManager().showNetworkExceptionMessage(e);
    }

    @Override
    public void setData(List<ContactModel> _data) {
        mContactListAdapter.setContactList(_data);
        listView.setAdapter(mContactListAdapter);
        getToolbarController().setSelectAllEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContactsPresenter.checkContactReadPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mContactsPresenter.onPause();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mContactsPresenter.selectAll(isChecked);
    }

    @Override
    public void skip() {
        AlertDialogModel model = new AlertDialogModel.Builder()
                .setTitle(getResources().getString(R.string.app_contact_skip_dialog_title))
                .setMessage(getResources().getString(R.string.app_contact_skip_dialog_message))
                .setPositiveText(getResources().getString(R.string.app_contact_dialog_earn_more))
                .setNegativeText(getResources().getString(R.string.contact_skip))
                .build();

        AlertDialogManager.showAlertDialog(this, model, new TwoButtonDialogListener() {
            @Override
            public void secondButtonClick() {
                startMainActivity();
            }

            @Override
            public void oneButtonClick() {
            }
        });
    }

    @Override
    public void setEnableInvite() {
        tvInvite.setEnabled(true);
    }

    @Override
    public void setDisableInvite() {
        tvInvite.setEnabled(false);
    }

    @Override
    public void notifyDataSetChange() {
        mContactListAdapter.notifyDataSetChanged();
    }

    @Override
    public int getToolbarId() {
        return R.id.toolbar_AC;
    }

    @Override
    public int getContainerId() {
        return R.id.rlContainer_AC;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.chContactStatus_ICL);
        if (checkBox.getVisibility() == View.VISIBLE) {
            checkBox.toggle();
        }
        mContactsPresenter.onItemClick(position - listView.getHeaderViewsCount());

    }

    @Override
    public int getProgressId() {
        return R.id.pbLoadingIndicator_AC;
    }

    @Override
    public void checkContactReadPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                showSnackBar();
            } else {
                ActivityCompat.requestPermissions(this,  new String[]{Manifest.permission.READ_CONTACTS},
                        Constants.REQUEST_READ_CONTACTS);
            }
        } else {
            mContactsPresenter.downloadData();
        }
    }

    @Override
    public void setEmptyListView() {
        tvEmptyList.setVisibility(View.VISIBLE);
        listView.setAdapter(mContactListAdapter);
        listView.setEmptyView(tvEmptyList);
        hideProgress();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mContactsPresenter.downloadData();
                } else {
                    mContactsPresenter.setEmptyListView();
                }
        }
    }

    private void showSnackBar() {
        Snackbar.make(coordinatorLayout, R.string.check_permission, Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(ContactActivity.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                Constants.REQUEST_READ_CONTACTS);
                    }
                })
                .setActionTextColor(setColor(android.R.color.white))
                .show();
    }

    @Override
    public void retryRequest() {
        mContactsPresenter.downloadData();
    }
}
