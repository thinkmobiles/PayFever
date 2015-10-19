package com.payfever.domain.services;

import com.payfever.data.model.StatisticModel;

import rx.Observable;

/**
 * Created by richi on 2015.10.19..
 */
public interface StatisticService {
    Observable<StatisticModel> getStatisticData();
}
