package com.payfever.domain.interactors.ringtone_interactor;

import com.payfever.data.services.ServiceProvider;

import rx.Observable;

/**
 * Created by richi on 2015.11.02..
 */
public class RingtoneInteractorImpl extends RingtoneInteractor {

    @Override
    protected Observable buildDownloadObserver(String _url, String _filePath) {
        return ServiceProvider.getInstance().getRingtoneService().downloadRingtone(_url, _filePath);
    }

    @Override
    protected Observable buildGetObserver() {
        return ServiceProvider.getInstance().getRingtoneService().getRingTones();
    }
}
