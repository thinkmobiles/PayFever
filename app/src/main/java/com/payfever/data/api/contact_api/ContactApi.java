package com.payfever.data.api.contact_api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.payfever.data.model.GetContactResponse;
import com.payfever.data.model.response.ContactListModel;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactApi {

    GetContactResponse getContactListData() throws ParseException;
    ContactListModel postContactData(List<String> _contacts) throws ParseException;
}
