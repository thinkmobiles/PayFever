package com.payfever.data.service_implementation.contact_service_impl;

import com.parse.ParseObject;
import com.payfever.data.api.contact_api.ContactApi;
import com.payfever.data.api.contact_api.ContactApiImpl;
import com.payfever.data.api.statistic_api.StatisticApi;
import com.payfever.data.api.statistic_api.StatisticApiImpl;
import com.payfever.data.model.ContactModel;
import com.payfever.data.model.StatisticModel;
import com.payfever.data.service_implementation.contact_service_impl.contact_logic.ContactProvider;
import com.payfever.data.service_implementation.contact_service_impl.contact_logic.ContactProviderImpl;
import com.payfever.data.transformators.BaseTransformation;
import com.payfever.data.transformators.ContactTransformatorImpl;
import com.payfever.data.transformators.StatisticTransformatorImpl;
import com.payfever.domain.services.contacts.ContactService;

import java.text.ParseException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactServiceImpl implements ContactService {

    private BaseTransformation<List<ContactModel>, List<ParseObject>> mTransformation;
    private ContactApi mContactApi;
    private ContactProvider mContactProvider;

    public ContactServiceImpl() {
        mTransformation = new ContactTransformatorImpl();
        mContactApi = new ContactApiImpl();
        mContactProvider = new ContactProviderImpl();
    }

    @Override
    public Observable<List<ContactModel>> getContactData() {
        return Observable.create(new Observable.OnSubscribe<List<ContactModel>>() {
            @Override
            public void call(Subscriber<? super List<ContactModel>> subscriber) {
                try {
                    List<ContactModel> contactModels = mTransformation.transform(mContactApi.getContactListData());
                    subscriber.onNext(contactModels);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e.getCause());
                }
            }
        }).map(new Func1<List<ContactModel>, List<ContactModel>>() {
            @Override
            public List<ContactModel> call(List<ContactModel> contactModels) {
                List<ContactModel> contactsFromDevice = mContactProvider.getContactsFromDevice();
                return mContactProvider.compareContactList(contactsFromDevice, contactModels);
            }
        });
    }

    @Override
    public Observable postContactData() {
        return null;
    }
}
