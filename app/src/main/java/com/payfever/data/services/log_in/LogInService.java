package com.payfever.data.services.log_in;

import com.payfever.data.model.LogInUserModel;

import rx.Observable;

/**
 * Created by richi on 2015.10.26..
 */
public interface LogInService {
    Observable logIn(LogInUserModel _model);
}
