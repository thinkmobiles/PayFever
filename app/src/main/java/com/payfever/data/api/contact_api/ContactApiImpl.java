package com.payfever.data.api.contact_api;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.payfever.data.model.GetContactResponse;
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
    public GetContactResponse getContactListData() throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", ParseUser.getCurrentUser().getObjectId());
        Map<String, List<String>> result = ParseCloud.callFunction("get_invitations", map);
        GetContactResponse model = new GetContactResponse();
        model.setmAll(result.get("all"));
        model.setmExpired(result.get("expired"));
        model.setmPending(result.get("pending"));

        return model;
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
