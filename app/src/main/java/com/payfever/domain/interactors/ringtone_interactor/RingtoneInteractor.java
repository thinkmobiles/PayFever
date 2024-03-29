package com.payfever.domain.interactors.ringtone_interactor;

import com.payfever.domain.basics.BaseInteractor;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by richi on 2015.11.02..
 */
public abstract class RingtoneInteractor extends BaseInteractor {
    protected Subscription mDownloadSubscription = Subscriptions.empty();
    protected Subscription mUpdateUserInfo = Subscriptions.empty();

    public RingtoneInteractor() {}

    @SuppressWarnings("unchecked")
    public void downloadRingtone(Observer _subscriber, String _url, String _filePath) {
        mGetSubscription = buildDownloadObserver(_url, _filePath)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_subscriber);
    }

    @SuppressWarnings("unchecked")
    public void updateUserProfile(Observer _subscriber, String _selectedItemId) {
        mGetSubscription = buildUpdateUserObserver(_selectedItemId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_subscriber);
    }
    @Override
    public void unSubscribe() {
        super.unSubscribe();
        if (!mDownloadSubscription.isUnsubscribed())
            mDownloadSubscription.unsubscribe();
        if (!mUpdateUserInfo.isUnsubscribed())
            mUpdateUserInfo.isUnsubscribed();
    }

    protected abstract Observable buildDownloadObserver(String _url, String _filePath);
    protected abstract Observable buildUpdateUserObserver(String _selectedFileItemId);
}
