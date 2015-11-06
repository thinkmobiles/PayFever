package com.payfever.presentation.fragment.set_ringtones;

import android.view.View;

import com.payfever.data.model.ringtone.Ringtone;

/**
 * Created by
 * mRogach on 03.11.2015.
 */

public interface OnPayToneItemListener {

    void playRingtone(Ringtone _model);
    void setPayTone(String _url, String _name);
}
