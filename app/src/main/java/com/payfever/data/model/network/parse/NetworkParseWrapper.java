package com.payfever.data.model.network.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by richi on 2015.11.04..
 */

@ParseClassName("Invitations")
public class NetworkParseWrapper extends ParseObject {

    public String getUserName() {
        return getObjectId();
    }

    public String getPushChannel() {
        return getObjectId();
    }

    public boolean isAccepted() {
        return getBoolean("isAccepted");
    }

    public String getPhoneNumber() {
        return getString("phoneNumber");
    }
}
