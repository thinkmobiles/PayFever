package com.payfever.data.api.statistic_api;

import com.parse.ParseObject;
import com.payfever.data.api.StatisticApi;

import java.text.ParseException;

/**
 * Created by richi on 2015.10.19..
 */
public class StatisticApiImpl implements StatisticApi {
    @Override
    public ParseObject getStatisticData() {
        //TODO: parse object from server
        return new ParseObject("Test");
    }
}
