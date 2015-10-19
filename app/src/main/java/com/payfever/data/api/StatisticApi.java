package com.payfever.data.api;

import com.parse.ParseObject;

import java.text.ParseException;

/**
 * Created by richi on 2015.10.19..
 */
public interface StatisticApi {
    ParseObject getStatisticData() throws ParseException;
}
