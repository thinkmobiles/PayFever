package com.payfever.domain.services;

/**
 * Created by richi on 2015.10.19..
 */
public class ServiceProvider {

    private static ServiceProvider sInstance;

    private StatisticService mStatisticService;

    private ServiceProvider() {
        //TODO: create service implementation
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
