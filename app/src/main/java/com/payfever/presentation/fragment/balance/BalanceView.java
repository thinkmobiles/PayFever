package com.payfever.presentation.fragment.balance;

import com.payfever.data.model.balance.BalanceModel;
import com.payfever.presentation.basics.BaseView;

/**
 * Created by richi on 2015.10.27..
 */
public interface BalanceView extends BaseView<BalanceModel> {
    void showServerError(Throwable e);
    void initChart();
    void initActionBar();
    void initMarker();
}
