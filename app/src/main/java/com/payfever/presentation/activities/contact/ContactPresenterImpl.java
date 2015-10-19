package com.payfever.presentation.activities.contact;

import android.os.Bundle;
import android.util.Log;

import com.payfever.data.model.ContactModel;
import com.payfever.domain.basics.BasePostGetInteractor;
import com.payfever.domain.interactors.contactInteractor.ContactInteractorImpl;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

import rx.Subscriber;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactPresenterImpl implements ContactPresenter {

    private ContactView mContactView;
    private BasePostGetInteractor<List<ContactModel>> contactInteractor;
    private List<ContactModel> contactList;

    public ContactPresenterImpl() {
        contactInteractor = new ContactInteractorImpl(PayFeverApplication.getApplication().getBackgroundHandler());
    }

    @Override
    public void setView(ContactView _view) {
        mContactView = _view;
    }

    @Override
    public void invite() {
        // TODO: imteractor
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
        contactInteractor.executeGET(getCallback);
    }

    @Override
    public void onPause() {
        contactInteractor.unSubscribe();
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

    private Subscriber<List<ContactModel>> getCallback = new Subscriber<List<ContactModel>>() {
        @Override
        public void onCompleted() {
            mContactView.setData(contactList);
            mContactView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
           Log.e("Subscriber", e.getMessage());
        }

        @Override
        public void onNext(List<ContactModel> contactModels) {
            contactList = contactModels;
        }
    };

}
