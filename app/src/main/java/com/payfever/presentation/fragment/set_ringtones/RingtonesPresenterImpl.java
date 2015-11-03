package com.payfever.presentation.fragment.set_ringtones;

import android.os.Bundle;
import android.util.Log;

import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.domain.interactors.ringtone_interactor.RingtoneInteractor;
import com.payfever.domain.interactors.ringtone_interactor.RingtoneInteractorImpl;
import com.payfever.presentation.utils.RingtoneController;

import java.util.List;

import rx.Subscriber;

/**
 * Created by
 * mRogach on 03.11.2015.
 */

public final class RingtonesPresenterImpl implements RingtonesPresenter {

    private RingtonesView mRingtonesView;
    private RingtoneInteractor mRingtoneInteractor;
    private List<Ringtone> mRingtones;
    private String ringtoneName = "";
    private String ringtonePath = "";

    public RingtonesPresenterImpl() {
        mRingtoneInteractor = new RingtoneInteractorImpl();
        ringtonePath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/download/";
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void setView(RingtonesView _view) {
        mRingtonesView = _view;
    }

    @Override
    public void downloadRingtones() {
        if (mRingtones == null ) {
            mRingtonesView.showProgress();
            mRingtoneInteractor.executeGET(new SubscriberRingToneList());
        }
    }

    @Override
    public void setPayTone(String _url, String _name) {
        ringtoneName = _name + ".mp3";
        mRingtoneInteractor.downloadRingtone(new SubscriberRingTone(), _url, ringtonePath + _name);
        mRingtonesView.showDownloadProgress();
    }

    @Override
    public void onStop() {
        mRingtoneInteractor.unSubscribe();
    }

    private class SubscriberRingToneList extends Subscriber<List<Ringtone>> {
        @Override
        public void onCompleted() {
            mRingtonesView.setData(mRingtones);
            mRingtonesView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Subscriber", e.getMessage());
        }

        @Override
        public void onNext(List<Ringtone> ringtoneList) {
            mRingtones = ringtoneList;
        }
    }

    private class SubscriberRingTone extends Subscriber<Integer> {
        @Override
        public void onCompleted() {
            RingtoneController.setRingtone(ringtonePath, ringtoneName);
            mRingtonesView.dismiss();
        }

        @Override
        public void onError(Throwable e) {
            mRingtonesView.dismiss();
//            Log.e("Subscriber", e.getMessage());
        }

        @Override
        public void onNext(Integer ringtone) {
            mRingtonesView.updateProgress(ringtone);
            Log.i("progress", "onNext " + ringtone);

        }
    }
}
