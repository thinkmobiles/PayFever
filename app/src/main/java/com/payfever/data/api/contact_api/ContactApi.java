package com.payfever.data.api.contact_api;

import com.parse.ParseObject;

import java.text.ParseException;
import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactApi {

    List<ParseObject> getContactListData() throws ParseException;
    void postContactData() throws ParseException;
}
