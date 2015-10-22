package com.payfever.data.services.contacts;

import com.payfever.data.model.ContactModel;

import java.util.List;

import rx.Observable;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactService {

    Observable<List<ContactModel>> getContactData();
    Observable postContactData(List<String> contacts);

}
