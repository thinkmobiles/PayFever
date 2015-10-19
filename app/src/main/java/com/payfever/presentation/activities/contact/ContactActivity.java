package com.payfever.presentation.activities.contact;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.ContactModel;
import com.payfever.presentation.basics.BaseActivity;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public class ContactActivity extends BaseActivity implements View.OnClickListener, ContactView {

    private ListView listView;
    private TextView tvInvite;
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
    }



    @Override
    public void onClick(View v) {

    }

    private void findViews() {
        listView = $(R.id.lvContacts_AC);
        tvInvite = $(R.id.tvInvite_AC);
    }

    private void initObjects() {
        mContactsPresenter = new ContactPresenterImpl();
        mContactListAdapter = new ContactListAdapter();
    }

    private void setListeners() {
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
    protected int getToolbarId() {
        return 0;
    }

    @Override
    protected int getContainerId() {
        return 0;
    }

}
