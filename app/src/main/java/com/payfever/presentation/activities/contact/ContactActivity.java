package com.payfever.presentation.activities.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.model.ContactModel;
import com.payfever.presentation.activities.main.MainActivity;
import com.payfever.presentation.basics.BaseActivity;
import com.payfever.presentation.dialogs.AlertDialogManager;
import com.payfever.presentation.dialogs.AlertDialogModel;
import com.payfever.presentation.dialogs.TwoButtonDialogListener;
import com.rey.material.widget.CheckBox;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public class ContactActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, ContactView, CompoundButton.OnCheckedChangeListener {

    private ListView listView;
    private TextView tvInvite;
    private TextView tvSkip;
    private ContactPresenter mContactsPresenter;
    private ContactListAdapter mContactListAdapter;


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
        mContactsPresenter.initialize(savedInstanceState);
    }

    private void initToolbarController() {
        getToolbarController().showSelectAll();
        getToolbarController().onCheckBoxClick(this);
        getToolbarController().setTitle("Import Contacts");
        setSupportActionBar(getToolbarController().getToolbar());
        getToolbarController().showBackButton(this);
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
        listView = $(R.id.lvContacts_AC);
        tvSkip = $(R.id.tvSkip_AC);
        tvInvite = $(R.id.tvInvite_AC);
    }

    private void initObjects() {
        mContactsPresenter = new ContactPresenterImpl();
        mContactListAdapter = new ContactListAdapter();
    }

    private void setListeners() {
        tvSkip.setOnClickListener(this);
        tvInvite.setOnClickListener(this);
        listView.setOnItemClickListener(this);
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
    public void setData(List<ContactModel> _data) {
        mContactListAdapter.setContactList(_data);
        listView.setAdapter(mContactListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContactsPresenter.downloadData();
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
                startActivity(MainActivity.getCallingIntent(ContactActivity.this));
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
}
