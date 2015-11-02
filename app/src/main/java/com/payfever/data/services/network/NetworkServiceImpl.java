package com.payfever.data.services.network;

import com.payfever.data.model.GetContactResponse;
import com.payfever.data.model.network.NetworkResponse;
import com.payfever.data.model.network.NetworkUserResponse;
import com.payfever.data.model.network.Statistic;
import com.payfever.data.services.ServiceProvider;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkServiceImpl implements NetworkService {

    @Override
    public Observable<NetworkResponse> getNetworkStatistic() {
        return Observable.zip(ServiceProvider.getInstance().getContactService().getContactResponse(),
                ServiceProvider.getInstance().getNetworkUserService().getNetworkUsers(),
                new Func2<GetContactResponse, NetworkUserResponse, NetworkResponse>() {
                    @Override
                    public NetworkResponse call(GetContactResponse contactModels, NetworkUserResponse networkUsers) {
                        NetworkResponse networkResponse = new NetworkResponse();
                        Statistic statistic = new Statistic();
                        networkResponse.setmNetworkStatistic(statistic);
                        statistic.setmUsers(networkUsers.getmUsers());
                        statistic.setmPending(contactModels.getmPending().size());
                        statistic.setmExpired(contactModels.getmExpired().size());
                        statistic.setmSentOut(contactModels.getmAll().size());
                        statistic.setmTotalNetworkAccepts(networkUsers.getmNetworkLevel() + networkUsers.getmFirstLevel());
                        statistic.setmFirstLevelAccepts(networkUsers.getmFirstLevel());
                        statistic.setmNetworkLevelAccepts(networkUsers.getmNetworkLevel());

                        return networkResponse;
                    }
                });
    }
}
