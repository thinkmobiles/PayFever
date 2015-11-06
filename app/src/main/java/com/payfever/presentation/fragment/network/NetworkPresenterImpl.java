package com.payfever.presentation.fragment.network;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.payfever.data.model.network.NetworkResponse;
import com.payfever.domain.basics.BaseInteractor;
import com.payfever.domain.interactors.network.NetworkInteractor;
import com.payfever.presentation.PayFeverApplication;
import com.payfever.presentation.global.Constants;

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
        mView.showProgress();
        mNetworkInteractor = new NetworkInteractor(PayFeverApplication.
                getApplication().getBackgroundHandler());
    }

    @Override
    public void onPause() {
        mNetworkInteractor.unSubscribe();
    }

    @Override
    public void onResume() {
        if (mData == null)
            downloadData();
//        mView.showFAB();
        mView.setTitle();
    }

    @Override
    public void downloadData() {
        mNetworkInteractor.executeGET(new DownloadListener());
    }

    @Override
    public void setView(NetworkView _view) {
        mView = _view;
    }

    @Override
    public void onItemClicked(int _position) {

    }

    @Override
    public void fabClicked() {
        mView.openInviteContacts();
    }

    @Override
    public void onActivityResult(int _resultCode, int _requestCode, Intent _data) {
        if (_requestCode == MyNetworkFragment.REQUEST_CODE_INVITE &&
                _resultCode == Activity.RESULT_OK && mData != null) {
            Integer successUser = _data.getIntExtra(Constants.EXTRA_INTEGER, 0);
            mData.getmNetworkStatistic()
                    .setmSentOut(mData.getmNetworkStatistic().getSentOut() + successUser);
            mView.setStaticData(mData.getmNetworkStatistic());
        }
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
            mView.showServerError(e);
        }

        @Override
        public void onNext(NetworkResponse _networkResponse) {
            mData = _networkResponse;
        }
    }
}
