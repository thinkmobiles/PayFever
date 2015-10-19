package com.payfever.data.service_implementation.contact_service_impl.contact_logic;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.payfever.data.model.ContactModel;
import com.payfever.presentation.PayFeverApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public final class ContactProviderImpl implements ContactProvider {

    public ContactProviderImpl() {
    }

    @Override
    public List<ContactModel> getContactsFromDevice() {
        List<ContactModel> contactList = new ArrayList<>();
        Cursor phones = PayFeverApplication.getApplication().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (phones != null) {
            if (phones.moveToFirst()) {
                do {
                    ContactModel contact = new ContactModel();
                    contact.setName(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                    contact.setPhoneNumber(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    contact.setAvatar(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)));
                    if (contactList.isEmpty() || !contactList.get(contactList.size() - 1).getPhoneNumber().equals(contact.getPhoneNumber())) {
                        contactList.add(contact);
                    }
                } while (phones.moveToNext());
                phones.close();
            }
        }
        return contactList;
    }

    @Override
    public List<ContactModel> compareContactList(List<ContactModel> _listDevice, List<ContactModel> _listServer) {
        List<ContactModel> list = new ArrayList<>(_listDevice);
        if (_listServer == null) return list;
        for (ContactModel contactModel : _listServer) {
            if (!TextUtils.isEmpty(contactModel.getStatus())) {
                for (ContactModel contactDeviceModel : list) {
                    if (contactDeviceModel.getPhoneNumber().equals(contactModel.getPhoneNumber())) {
                        contactDeviceModel.setStatus(contactModel.getStatus());
                    }
                }
            }
        }
        return list;
    }

}
