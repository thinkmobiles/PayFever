package com.payfever.presentation.fragment.network;

import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by richi on 2015.10.21..
 */
public interface NetworkPresenter extends BasePresenter<NetworkView> {
    void onItemClicked(int _position);
}
