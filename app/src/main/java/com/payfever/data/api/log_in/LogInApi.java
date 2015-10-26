package com.payfever.data.api.log_in;

import com.parse.ParseException;
import com.payfever.data.model.LogInUserModel;


/**
 * Created by richi on 2015.10.26..
 */
public interface LogInApi {
    void logIn(LogInUserModel _model) throws ParseException;
}
