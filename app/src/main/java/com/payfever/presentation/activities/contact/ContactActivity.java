package com.payfever.presentation.activities.contact;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.model.ContactModel;
import com.payfever.presentation.basics.BaseActivity;
import com.payfever.presentation.dialogs.AlertDialogManager;
import com.payfever.presentation.dialogs.AlertDialogModel;
import com.payfever.presentation.dialogs.TwoButtonDialogListener;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public class ContactActivity extends BaseActivity implements View.OnClickListener, ContactView, CompoundButton.OnCheckedChangeListener {

    private ListView listView;
    private TextView tvInvite;
    private TextView tvSkip;
    private ContactPresenter mContactsPresenter;
    private ContactListAdapter mContactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        findViews();
        initObjects();
        setListeners();
        mContactsPresenter.setView(this);
        mContactsPresenter.initialize(savedInstanceState);
        View view = View.inflate(this, R.layout.header_contact_list, null);
        AppCompatCheckBox checkBox = (AppCompatCheckBox) view.findViewById(R.id.chContactStatus_HCL);
        checkBox.setOnCheckedChangeListener(this);
        listView.addHeaderView(view);
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
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setData(List<ContactModel> _data) {
        mContactListAdapter.setContactList(_data);
        listView.setAdapter(mContactListAdapter);
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
                Toast.makeText(ContactActivity.this, "Skip", Toast.LENGTH_LONG).show();
            }

            @Override
            public void oneButtonClick() {
                Toast.makeText(ContactActivity.this, "Earn monney", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected int getToolbarId() {
        return 0;
    }

    @Override
    protected int getContainerId() {
        return 0;
    }

}
