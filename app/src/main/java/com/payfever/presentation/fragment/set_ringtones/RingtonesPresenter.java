package com.payfever.presentation.fragment.set_ringtones;

import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by
 * mRogach on 03.11.2015.
 */

public interface RingtonesPresenter extends BasePresenter<RingtonesView> {
    void downloadRingtones();
    void setPayTone(Ringtone _ringtone);
    void onStart();
    void onStop();
    void playRingtone(Ringtone ringtone);
    void stopPlaying();
}
