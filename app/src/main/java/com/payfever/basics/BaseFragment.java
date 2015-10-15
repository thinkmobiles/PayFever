package com.payfever.basics;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by richi on 2015.10.15..
 */
public abstract class BaseFragment extends Fragment {

    private int mLayoutRes = -1;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mLayoutRes == -1)
            throw new RuntimeException("You have to call setContentView!");

        mRootView = inflater.inflate(mLayoutRes, container, false);
        return mRootView;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int _resId) {
        return (T) mRootView.findViewById(_resId);
    }

    protected void setContentView(@LayoutRes int _resLayout) {
        mLayoutRes = _resLayout;
    }
}
