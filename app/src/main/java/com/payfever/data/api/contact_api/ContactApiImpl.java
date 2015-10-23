package com.payfever.data.api.contact_api;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.payfever.data.model.response.ContactListModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactApiImpl implements ContactApi {

    @Override
    public List<ParseObject> getContactListData() throws ParseException {
        //TODO: parse object from server
        Map<String, Object> map = new HashMap<>();
        map.put("userId", ParseUser.getCurrentUser().getObjectId());
        Map<String, String> result = ParseCloud.callFunction("get_invitations", map);
        return null;
    }

    @Override
    public ContactListModel postContactData(final List<String> _contacts) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", ParseUser.getCurrentUser().getObjectId());
        map.put("phoneNumberList", _contacts);
        Map<String, List<String>> map1 = ParseCloud.callFunction("send_invitation_list", map);

        ContactListModel contactListModel = new ContactListModel();
        contactListModel.setFailedContacts(map1.get("failedPhoneNumbers"));
        contactListModel.setAllContacts(map1.get("allPhoneNumber"));
        contactListModel.setSuccessContacts(map1.get("succeedPhoneNumbers"));
        return contactListModel;
    }
}
