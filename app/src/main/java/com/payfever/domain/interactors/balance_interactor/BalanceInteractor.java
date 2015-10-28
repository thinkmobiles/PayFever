package com.payfever.domain.interactors.balance_interactor;

import com.payfever.data.services.ServiceProvider;
import com.payfever.domain.basics.BaseInteractor;

import rx.Observable;

/**
 * Created by richi on 2015.10.27..
 */
public class BalanceInteractor extends BaseInteractor {

    @Override
    protected Observable buildGetObserver() {
        return ServiceProvider.getInstance().getBalanceService().getBalance();
    }
}
