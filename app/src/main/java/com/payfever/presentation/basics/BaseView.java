package com.payfever.presentation.basics;


/**
 * Created by
 * mRogach on 19.10.2015.
 */

public interface BaseView<T> {
    void showProgress();
    void hideProgress();
    void setData(T _data);
}
