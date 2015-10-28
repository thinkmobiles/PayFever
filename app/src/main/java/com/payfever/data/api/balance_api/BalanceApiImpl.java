package com.payfever.data.api.balance_api;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.payfever.data.model.balance.BalanceModel;

import java.util.HashMap;

/**
 * Created by richi on 2015.10.27..
 */
public class BalanceApiImpl implements BalanceApi {

    @Override
    public BalanceModel getBalance() throws ParseException {
//        HashMap<String, Integer> map = ParseCloud.callFunction("get_balance", );
        BalanceModel model = new BalanceModel();
        model.setmAverage(568);
        model.setmBalance(6476);
        return model;
    }
}
