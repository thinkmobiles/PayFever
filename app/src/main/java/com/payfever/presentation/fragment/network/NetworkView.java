package com.payfever.presentation.fragment.network;

import com.payfever.data.model.network.NetworkResponse;
import com.payfever.data.model.network.Statistic;
import com.payfever.presentation.basics.BaseView;

/**
 * Created by richi on 2015.10.21..
 */
public interface NetworkView extends BaseView<NetworkResponse> {
    void setStaticData(Statistic _statistic);
}
