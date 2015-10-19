package com.payfever.presentation.activities.contact;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.presentation.basics.BaseActivity;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public class ContactActivity extends BaseActivity implements View.OnClickListener, ContactView {

    private ListView listView;
    private TextView tvInvite;
    private ContactPresenter mContactsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        findViews();
        setListeners();
        mContactsPresenter = new ContactPresenterImpl();
        mContactsPresenter.setView(this);
    }



    @Override
    public void onClick(View v) {

    }

    private void findViews() {
        listView = $(R.id.lvContacts_AC);
        tvInvite = $(R.id.tvInvite_AC);
    }

    private void setListeners() {
        tvInvite.setOnClickListener(this);
    }

    @Override
    public void getContactList() {

    }

    @Override
    public void invite() {

    }

    @Override
    public void initialize(Bundle _savedInstanceState) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setData(Object _data) {

    }
}
