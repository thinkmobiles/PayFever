package com.payfever.data.api.balance_api;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.payfever.data.model.balance.BalanceModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by richi on 2015.10.27..
 */
public class BalanceApiImpl implements BalanceApi {

    @Override
    public BalanceModel getBalance() throws ParseException {
        Map<String, String> query = new HashMap<>();
        query.put("userId", ParseUser.getCurrentUser().getObjectId());
        Map<String, Integer> map = ParseCloud.callFunction("get_user_balance", query);
        BalanceModel model = new BalanceModel();
        model.setmAverage(map.get("monthlyAvg"));
        model.setmBalance(map.get("currentBalance"));
        return model;
    }
}
