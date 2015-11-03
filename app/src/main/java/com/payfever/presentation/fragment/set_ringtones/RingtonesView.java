package com.payfever.presentation.fragment.set_ringtones;

import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.presentation.basics.BaseView;

import java.util.List;

/**
 * Created by
 * mRogach on 03.11.2015.
 */

public interface RingtonesView extends BaseView<List<Ringtone>> {

    void showDownloadProgress();
    void updateProgress(Integer _value);
    void dismiss();
}
