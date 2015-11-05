package com.payfever.data.services.contacts;

import com.parse.ParseException;
import com.payfever.data.api.contact_api.ContactApi;
import com.payfever.data.api.contact_api.ContactApiImpl;
import com.payfever.data.exceptions.OnSubscribeWithNetworkCheck;
import com.payfever.data.model.ContactModel;
import com.payfever.data.model.GetContactResponse;
import com.payfever.data.model.response.ContactListModel;
import com.payfever.data.services.contacts.contact_logic.ContactProvider;
import com.payfever.data.services.contacts.contact_logic.ContactProviderImpl;
import com.payfever.data.transformators.BaseTransformation;
import com.payfever.data.transformators.ContactTransformatorImpl;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactServiceImpl implements ContactService {

    private BaseTransformation<List<ContactModel>, GetContactResponse> mTransformation;
    private ContactApi mContactApi;
    private ContactProvider mContactProvider;

    public ContactServiceImpl() {
        mTransformation = new ContactTransformatorImpl();
        mContactApi = new ContactApiImpl();
        mContactProvider = new ContactProviderImpl();
    }

    @Override
    public Observable<GetContactResponse> getContactResponse() {
        return Observable.create(new OnSubscribeWithNetworkCheck<GetContactResponse>() {
            @Override
            public void call(Subscriber<? super GetContactResponse> subscriber) {
                super.call(subscriber);
                try {
                    GetContactResponse model = mContactApi.getContactListData();
                    subscriber.onNext(model);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }

    @Override
    public Observable<List<ContactModel>> getContactData() {
        return getContactResponse()
                .map(new Func1<GetContactResponse, List<ContactModel>>() {
                    @Override
                    public List<ContactModel> call(GetContactResponse getContactResponse) {
                        return mTransformation.transform(getContactResponse);
                    }
                })
                .map(new Func1<List<ContactModel>, List<ContactModel>>() {
                    @Override
                    public List<ContactModel> call(List<ContactModel> contactModels) {
                        List<ContactModel> contactsFromDevice = mContactProvider.getContactsFromDevice();
                        return mContactProvider.compareContactList(contactsFromDevice, contactModels);
                    }
                });
    }

    @Override
    public Observable<ContactListModel> postContactData(final List<String> _contacts) {
        return Observable.create(new OnSubscribeWithNetworkCheck<ContactListModel>() {
            @Override
            public void call(Subscriber<? super ContactListModel> subscriber) {
                super.call(subscriber);
                try {
                    ContactListModel contacts = mContactApi.postContactData(_contacts);
                    subscriber.onNext(contacts);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        });
    }
}
