package com.payfever.presentation.activities.contact;

import android.content.Intent;

import com.payfever.data.model.ContactModel;
import com.payfever.presentation.basics.BasePresenter;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactPresenter extends BasePresenter<ContactView> {
    void sendSMSToUsers();
    void invite();
    void skip();
    void selectAll(final boolean _checked);
    void onItemClick(final int _position);
    void downloadData();
    void checkContactReadPermission();
    void setEmptyListView();
    void setExtra(Intent _intent);
}
