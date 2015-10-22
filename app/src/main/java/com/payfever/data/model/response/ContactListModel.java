package com.payfever.data.model.response;


import java.util.List;

/**
 * Created by
 * mRogach on 22.10.2015.
 */

public final class ContactListModel {

    private List<String> mAllContacts;

    private List<String> mFailedContacts;

    private List<String> mSuccessContacts;

    public List<String> getAllContacts() {
        return mAllContacts;
    }

    public void setAllContacts(List<String> mAllContacts) {
        this.mAllContacts = mAllContacts;
    }

    public List<String> getSuccessContacts() {
        return mSuccessContacts;
    }

    public void setSuccessContacts(List<String> mSuccessContacts) {
        this.mSuccessContacts = mSuccessContacts;
    }

    public List<String> getFailedContacts() {
        return mFailedContacts;
    }

    public void setFailedContacts(List<String> mFailedContacts) {
        this.mFailedContacts = mFailedContacts;
    }
}
