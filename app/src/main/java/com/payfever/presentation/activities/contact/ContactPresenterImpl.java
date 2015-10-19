package com.payfever.presentation.activities.contact;

import android.os.Bundle;

import com.payfever.data.model.ContactModel;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactPresenterImpl implements ContactPresenter {

    private ContactView mContactView;


    @Override
    public void setView(ContactView _view) {
        mContactView = _view;
    }

    @Override
    public void getContactList() {
        mContactView.getContactList();
    }

    @Override
    public void invite() {
        mContactView.invite();
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
        mContactView.initialize(_savedInstanceState);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void showProgress() {
        mContactView.showProgress();
    }

    @Override
    public void hideProgress() {
        mContactView.hideProgress();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setData(List<ContactModel> _data) {
        mContactView.setData(_data);
    }

}
