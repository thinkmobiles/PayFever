package com.payfever.data.api.log_in;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.payfever.data.model.LogInUserModel;

/**
 * Created by richi on 2015.10.26..
 */
public class LogInApiImpl implements LogInApi {

    @Override
    public void logIn(LogInUserModel _model) throws ParseException {
        ParseUser.logIn(_model.getUserName(), _model.getPassword());
    }
}
