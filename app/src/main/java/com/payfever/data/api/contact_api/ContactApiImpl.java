package com.payfever.data.api.contact_api;

import com.parse.ParseObject;

import java.text.ParseException;
import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactApiImpl implements ContactApi {

    @Override
    public List<ParseObject> getContactListData() throws ParseException {
        //TODO: parse object from server
        return null;
    }

    @Override
    public void postContactData() throws ParseException {
        //TODO: parse object to server
    }
}
