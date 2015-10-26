package com.payfever.data.services.contacts.contact_logic;

import com.payfever.data.model.ContactModel;
import com.payfever.data.model.GetContactResponse;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactProvider {

    List<ContactModel> getContactsFromDevice();
    List<ContactModel> compareContactList(List<ContactModel> _listDevice, List<ContactModel> _listServer);
}
