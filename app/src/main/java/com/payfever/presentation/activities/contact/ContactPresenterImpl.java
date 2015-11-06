package com.payfever.presentation.activities.contact;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import com.payfever.data.model.ContactModel;
import com.payfever.data.model.ContactStatus;
import com.payfever.data.model.response.ContactListModel;
import com.payfever.domain.basics.BasePostGetInteractor;
import com.payfever.domain.interactors.contactInteractor.ContactInteractorImpl;
import com.payfever.presentation.PayFeverApplication;
import com.payfever.presentation.dialogs.AlertDialogManager;
import com.payfever.presentation.global.Constants;

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
    private ContactListModel mContactListModel;
    private SmsManager mSmsManager;
    private boolean mIsFromNetwork;

    public ContactPresenterImpl() {
        contactInteractor = new ContactInteractorImpl(PayFeverApplication.getApplication().getBackgroundHandler());
        contactListToInvite = new ArrayList<>();
        mSmsManager = SmsManager.getDefault();
    }

    @Override
    public void setView(ContactView _view) {
        mContactView = _view;
    }

    @Override
    public void setExtra(Intent _intent) {
        mIsFromNetwork = _intent.getBooleanExtra(Constants.FROM_NETWORK_FRAGMENT, false);
    }

    @Override
    public void invite() {
        mContactView.showInviteProgress();
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
            mContactView.showProgress();
            contactInteractor.executeGET(new SubscriberListContact());
        }
    }

    @Override
    public void checkContactReadPermission() {
        mContactView.checkContactReadPermission();
    }

    @Override
    public void setEmptyListView() {
        mContactView.setEmptyListView();
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
        hideSkipBtnIfNeed();
    }

    private void hideSkipBtnIfNeed() {
        if (mIsFromNetwork) {
            mContactView.hideSkipShowBack();
        }
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
            mContactView.showServerError(e);
        }

        @Override
        public void onNext(List<ContactModel> contactModels) {
            contactList = contactModels;
        }
    }

    private class SubscriberContactsToInvite extends Subscriber<ContactListModel> {
        @Override
        public void onCompleted() {
            mContactView.hideInviteProgress();

            mContactView.showSendSmsDialog();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Subscriber", e.getMessage());
            mContactView.showServerError(e);
        }

        @Override
        public void onNext(ContactListModel contactModels) {
            mContactListModel = contactModels;
        }
    }

    @Override
    public void sendSMSToUsers() {
        if (mContactListModel == null) {
            startNetworkMainActivity();
            return;
        }

        List<String> successNumbers = mContactListModel.getSuccessContacts();
        for (String _number : successNumbers) {
            mSmsManager.sendTextMessage(_number, null, "Check out PayFever for your smartphone!", null, null);
        }

        mContactView.result(successNumbers.size());
        startNetworkMainActivity();
    }

    private void checkValidateList() {
        if (contactListToInvite.isEmpty()) {
            mContactView.setDisableInvite();
        } else {
            mContactView.setEnableInvite();
        }
    }

    private void startNetworkMainActivity() {
        if (!mIsFromNetwork)
            mContactView.startMainActivity();
        else
            mContactView.onBack();
    }

}
