package com.payfever.presentation.fragment.network;

import android.os.Bundle;

import com.payfever.data.model.network.NetworkResponse;
import com.payfever.domain.basics.BaseInteractor;
import com.payfever.domain.interactors.network.NetworkInteractor;
import com.payfever.presentation.PayFeverApplication;

import rx.Subscriber;

/**
 * Created by richi on 2015.10.21..
 */
public class NetworkPresenterImpl implements NetworkPresenter {

    private NetworkView mView;

    private NetworkResponse mData;
    private BaseInteractor mNetworkInteractor;

    @Override
    public void initialize(Bundle _savedInstanceState) {
        mNetworkInteractor = new NetworkInteractor(PayFeverApplication.
                getApplication().getBackgroundHandler());
        mNetworkInteractor.executeGET(new DownloadListener());
        mView.showFAB();

        if (mData == null)
            mNetworkInteractor.executeGET(new DownloadListener());
    }

    @Override
    public void onPause() {
        mNetworkInteractor.unSubscribe();
        mView.hideFAB();
    }

    @Override
    public void setView(NetworkView _view) {
        mView = _view;
        mView.setTitle();
    }

    @Override
    public void onItemClicked(int _position) {

    }

    private class DownloadListener extends Subscriber<NetworkResponse> {
        @Override
        public void onCompleted() {
            mView.setData(mData);
            mView.setStaticData(mData.getmNetworkStatistic());
            mView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(NetworkResponse _networkResponse) {
            mData = _networkResponse;
        }
    }
}
