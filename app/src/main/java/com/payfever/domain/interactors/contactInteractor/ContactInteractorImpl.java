package com.payfever.domain.interactors.contactInteractor;

import android.os.Handler;

import com.payfever.data.model.ContactModel;
import com.payfever.domain.basics.BasePostGetInteractor;
import com.payfever.data.services.ServiceProvider;

import java.util.List;

import rx.Observable;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public class ContactInteractorImpl extends BasePostGetInteractor<List<ContactModel>> {

    public ContactInteractorImpl(Handler _handler) {
        super(_handler);
    }

    @Override
    public Observable buildPostObservable() {
        return ServiceProvider.getInstance().getContactService().postContactData();
    }

    @Override
    public Observable buildGetObserver() {
        return ServiceProvider.getInstance().getContactService().getContactData();
    }
}
