package com.payfever.presentation.fragment.set_ringtones;

import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by
 * mRogach on 03.11.2015.
 */

public interface RingtonesPresenter extends BasePresenter<RingtonesView> {
    void downloadRingtones();
    void setPayTone(String _url, String _name);
    void onStop();
}
