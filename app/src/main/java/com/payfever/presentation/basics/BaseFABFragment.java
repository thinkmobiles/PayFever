package com.payfever.presentation.basics;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.payfever.presentation.controllers.FABController;

/**
 * Created by richi on 2015.10.22..
 */
public abstract class BaseFABFragment extends BaseFragment {

    private FABController mFabController;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFabController = ((FABActivity) mActivity).getFABController();
    }

    protected FABController getFabController() {
        return mFabController;
    }
}
