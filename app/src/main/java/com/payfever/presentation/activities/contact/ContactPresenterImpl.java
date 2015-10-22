package com.payfever.presentation.activities.contact;

import android.os.Bundle;
import android.util.Log;

import com.payfever.data.model.ContactModel;
import com.payfever.data.model.ContactStatus;
import com.payfever.data.model.response.ContactListModel;
import com.payfever.domain.basics.BasePostGetInteractor;
import com.payfever.domain.interactors.contactInteractor.ContactInteractorImpl;
import com.payfever.presentation.PayFeverApplication;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactPresenterImpl implements ContactPresenter {

    private ContactView mContactView;
    private BasePostGetInteractor<List<String>> contactInteractor;
    private List<ContactModel> contactList;
    private List<ContactModel> contactListToInvite;
    private ContactListModel contactListModel;

    public ContactPresenterImpl() {
        contactInteractor = new ContactInteractorImpl(PayFeverApplication.getApplication().getBackgroundHandler());
        contactListToInvite = new ArrayList<>();
    }

    @Override
    public void setView(ContactView _view) {
        mContactView = _view;
    }

    @Override
    public void invite() {
        List<String> contacts = new ArrayList<>();
        for (ContactModel contactModel : contactListToInvite) {
            if (contactModel.getStatus() == ContactStatus.CHECKED.getStatus()) {
                contacts.add(contactModel.getPhoneNumber());
            }
        }
        contactInteractor.executePost(contacts, new SubscriberContactsToInvite());
    }

    @Override
    public void skip() {
        mContactView.skip();
    }

    @Override
    public void selectAll(final boolean _isChecked) {
        for (ContactModel contactModel : contactList) {
            if (_isChecked) {
                if (contactModel.getStatus() == ContactStatus.UNCHECKED.getStatus()) {
                    contactModel.setStatus(ContactStatus.CHECKED.getStatus());
                    contactListToInvite.add(contactModel);
                }
            } else {
                if (contactModel.getStatus() == ContactStatus.CHECKED.getStatus()) {
                    contactModel.setStatus(ContactStatus.UNCHECKED.getStatus());
                    if (!contactListToInvite.isEmpty() && contactListToInvite.contains(contactModel)) {
                        contactListToInvite.remove(contactModel);
                    }
                }
            }
        }
        checkValidateList();
        mContactView.notifyDataSetChange();
    }

    @Override
    public void onItemClick(final int _position) {
        if (contactList.get(_position).getStatus() == ContactStatus.UNCHECKED.getStatus()) {
            contactList.get(_position).setStatus(ContactStatus.CHECKED.getStatus());
            contactListToInvite.add(contactList.get(_position));
        } else if (contactList.get(_position).getStatus() == ContactStatus.CHECKED.getStatus()) {
            contactList.get(_position).setStatus(ContactStatus.UNCHECKED.getStatus());
            if (!contactListToInvite.isEmpty() && contactListToInvite.contains(contactList.get(_position))) {
                contactListToInvite.remove(contactList.get(_position));
            }
        }
        checkValidateList();
    }

    @Override
    public void downloadData() {
        if (contactList == null) {
            contactInteractor.executeGET(new SubscriberListContact());
        }
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
    }

    @Override
    public void onPause() {
        contactInteractor.unSubscribe();
    }


    private class SubscriberListContact extends Subscriber<List<ContactModel>> {
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
    }

    private class SubscriberContactsToInvite extends Subscriber<ContactListModel> {
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
        public void onNext(ContactListModel contactModels) {
            contactListModel = contactModels;
        }
    }

    private void checkValidateList() {
        if (contactListToInvite.isEmpty()) {
            mContactView.setDisableInvite();
        } else {
            mContactView.setEnableInvite();
        }
    }

}
