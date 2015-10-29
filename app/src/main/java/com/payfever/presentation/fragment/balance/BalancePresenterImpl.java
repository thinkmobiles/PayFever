package com.payfever.presentation.fragment.balance;

import android.os.Bundle;

import com.payfever.data.model.balance.BalanceModel;
import com.payfever.domain.interactors.balance_interactor.BalanceInteractor;

import rx.Observer;

/**
 * Created by richi on 2015.10.27..
 */
public class BalancePresenterImpl implements BalancePresenter {

    private BalanceInteractor mInteractor;
    private BalanceView mView;

    private BalanceModel mData;

    public BalancePresenterImpl() {
        mInteractor = new BalanceInteractor();
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
        if (mData == null)
            downloadData();
        else
            mView.hideProgress();

        mView.initMarker();
    }

    private void downloadData() {
        mView.showProgress();
        mInteractor.executeGET(new DownloadListener());
    }

    @Override
    public void onPause() {
        mInteractor.unSubscribe();
    }

    @Override
    public void setView(BalanceView _view) {
        mView = _view;
        mView.initActionBar();
    }

    private class DownloadListener implements Observer<BalanceModel> {
        @Override
        public void onCompleted() {
            mView.setData(mData);
            mView.initChart();
            mView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            mView.showServerError(e.getMessage());
        }

        @Override
        public void onNext(BalanceModel _balanceModel) {
            mData = _balanceModel;
        }
    }
}
