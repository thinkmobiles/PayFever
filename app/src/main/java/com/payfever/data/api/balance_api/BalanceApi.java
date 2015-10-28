package com.payfever.data.api.balance_api;

import com.parse.ParseException;
import com.payfever.data.model.balance.BalanceModel;

/**
 * Created by richi on 2015.10.27..
 */
public interface BalanceApi {
    BalanceModel getBalance() throws ParseException;
}
