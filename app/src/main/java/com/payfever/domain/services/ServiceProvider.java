package com.payfever.domain.services;

import com.payfever.data.service_implementation.StatisticServiceImpl;
import com.payfever.data.service_implementation.contact_service_impl.ContactServiceImpl;
import com.payfever.domain.services.contacts.ContactService;
import com.payfever.domain.services.statistic.StatisticService;

/**
 * Created by richi on 2015.10.19..
 */
public class ServiceProvider {

    private static ServiceProvider sInstance;

    private StatisticService mStatisticService;
    private ContactService mContactService;

    private ServiceProvider() {
        //TODO: create service implementation
        mStatisticService = new StatisticServiceImpl();
        mContactService = new ContactServiceImpl();
    }

    public static ServiceProvider getInstance() {
        if (sInstance == null)
            sInstance = new ServiceProvider();

        return sInstance;
    }

    public StatisticService getStatisticService() {
        return mStatisticService;
    }

    public ContactService getContactService() {
        return mContactService;
    }
}
