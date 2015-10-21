package com.payfever.data.api.register_api;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.payfever.data.model.UserModel;

/**
 * Created by richi on 2015.10.20..
 */
public class RegisterApImpl implements RegisterApi {
    @Override
    public ParseUser register(UserModel _userModel) throws ParseException {
        return ParseCloud.callFunction("sign_up_user", _userModel.getPostMap());
    }
}
