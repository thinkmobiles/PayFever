package com.payfever.presentation.fragment.set_ringtones;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.domain.interactors.ringtone_interactor.RingtoneInteractor;
import com.payfever.domain.interactors.ringtone_interactor.RingtoneInteractorImpl;
import com.payfever.presentation.utils.RingtoneController;

import java.io.IOException;
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
    private MediaPlayer mMediaPlayer;

    public RingtonesPresenterImpl() {
        mRingtoneInteractor = new RingtoneInteractorImpl();
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {
        ringtonePath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/download/";
        mMediaPlayer = new MediaPlayer();
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
        ringtoneName = _name;
        mRingtoneInteractor.downloadRingtone(new SubscriberRingTone(), _url, ringtonePath + _name);
        mRingtonesView.showDownloadProgress();
    }

    @Override
    public void onStart() {
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public void onStop() {
        mRingtoneInteractor.unSubscribe();
        setPlayingAllFalse();
        mRingtonesView.notifyData();
    }

    @Override
    public void playRingtone(Ringtone _ringtone) {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying() && _ringtone.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                setPlayingAllFalse();
                mRingtonesView.notifyData();
            } else {
                try {
                    setPlayingAllFalse();
                    _ringtone.setIsPlaying(true);
                    mRingtonesView.notifyData();
                    mMediaPlayer.reset();
                    mMediaPlayer.setDataSource(_ringtone.getUrlToFile());
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mRingtonesView.notifyData();
    }

    @Override
    public void stopPlaying() {
        mMediaPlayer.release();
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
            mRingtonesView.showServerError(e);
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

    private void setPlayingAllFalse() {
        if (mRingtones == null)
            return;


        for (Ringtone ringtone : mRingtones) {
            ringtone.setIsPlaying(false);
        }
    }
}
