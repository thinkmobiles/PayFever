package com.payfever.domain.services;

import com.payfever.data.service_implementation.StatisticServiceImpl;

/**
 * Created by richi on 2015.10.19..
 */
public class ServiceProvider {

    private static ServiceProvider sInstance;

    private StatisticService mStatisticService;

    private ServiceProvider() {
        //TODO: create service implementation
        mStatisticService = new StatisticServiceImpl();
    }

    public static ServiceProvider getInstance() {
        if (sInstance == null)
            sInstance = new ServiceProvider();

        return sInstance;
    }

    public StatisticService getStatisticService() {
        return mStatisticService;
    }
}
