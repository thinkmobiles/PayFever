package com.payfever.presentation.activities.contact;


import com.payfever.data.model.ContactModel;
import com.payfever.presentation.basics.BaseView;

import java.util.List;

/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface ContactView extends BaseView<List<ContactModel>> {

    void skip();
    void setEnableInvite();
    void setDisableInvite();
    void notifyDataSetChange();
    void checkContactReadPermission();
    void setEmptyListView();
}
