package com.payfever.data.transformators;

import com.payfever.data.model.ContactModel;
import com.payfever.data.model.ContactStatus;
import com.payfever.data.model.GetContactResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactTransformatorImpl implements BaseTransformation<List<ContactModel>, GetContactResponse> {

    @Override
    public List<ContactModel> transform(GetContactResponse _data) {
        List<ContactModel> transformModel = new ArrayList<>();
        for (String expiredNumber : _data.getmExpired()) {
            ContactModel contactModel = new ContactModel();
            contactModel.setPhoneNumber(expiredNumber);
            contactModel.setStatus(ContactStatus.EXPIRED.getStatus());
            transformModel.add(contactModel);
        }

        for (String expiredNumber : _data.getmPending()) {
            ContactModel contactModel = new ContactModel();
            contactModel.setPhoneNumber(expiredNumber);
            contactModel.setStatus(ContactStatus.PENDING.getStatus());
            transformModel.add(contactModel);
        }

        return transformModel;
    }
}
