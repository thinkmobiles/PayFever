package com.payfever.data.api.register_api;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.payfever.data.model.UserModel;


/**
 * Created by richi on 2015.10.20..
 */
public interface RegisterApi {
    ParseUser register(UserModel _model) throws ParseException;
}
