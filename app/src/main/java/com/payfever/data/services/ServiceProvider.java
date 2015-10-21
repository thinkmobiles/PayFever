package com.payfever.data.services;

import com.payfever.data.services.network.NetworkService;
import com.payfever.data.services.network.NetworkServiceImpl;
import com.payfever.data.services.registration.RegisterService;
import com.payfever.data.services.registration.RegisterServiceImpl;
import com.payfever.data.services.statistic.StatisticServiceImpl;
import com.payfever.data.services.contacts.ContactServiceImpl;
import com.payfever.data.services.contacts.ContactService;
import com.payfever.data.services.statistic.StatisticService;

/**
 * Created by richi on 2015.10.19..
 */
public class ServiceProvider {

    private static ServiceProvider sInstance;

    private StatisticService mStatisticService;
    private ContactService mContactService;
    private RegisterService mRegisterService;
    private NetworkService mNetworkService;

    private ServiceProvider() {
        mStatisticService = new StatisticServiceImpl();
        mContactService = new ContactServiceImpl();
        mRegisterService = new RegisterServiceImpl();
        mNetworkService = new NetworkServiceImpl();
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

    public RegisterService getRegisterService() {
        return mRegisterService;
    }

    public NetworkService getNetworkService() {
        return mNetworkService;
    }
}
