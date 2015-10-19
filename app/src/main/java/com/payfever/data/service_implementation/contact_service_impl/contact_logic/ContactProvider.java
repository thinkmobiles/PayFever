package com.payfever.data.service_implementation.contact_service_impl.contact_logic;

import com.payfever.data.model.ContactModel;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactProvider {

    List<ContactModel> getContactsFromDevice();
    List<ContactModel> compareContactList(List<ContactModel> _listDevice, List<ContactModel> _listServer);
}
