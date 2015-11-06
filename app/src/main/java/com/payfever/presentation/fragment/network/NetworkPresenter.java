package com.payfever.presentation.fragment.network;

import android.content.Intent;

import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by richi on 2015.10.21..
 */
public interface NetworkPresenter extends BasePresenter<NetworkView> {
    void onItemClicked(int _position);
    void fabClicked();
    void onResume();
    void downloadData();
    void onActivityResult(int _resultCode, int _requestCode, Intent _data);
}
