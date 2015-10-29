package com.payfever.presentation.fragment.set_ringtones;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.payfever.R;
import com.payfever.presentation.basics.BaseFABFragment;

/**
 * Created by richi on 2015.10.28..
 */
public class RingtonesFragment extends BaseFABFragment {

    public static BaseFABFragment newInstance() {
        Bundle args = new Bundle();

        BaseFABFragment fabFragment = new RingtonesFragment();
        fabFragment.setArguments(args);

        return fabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ringtones);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoadingManager().hideProgress();
        getToolbarController().setTitle("Ringtone");
    }
}
