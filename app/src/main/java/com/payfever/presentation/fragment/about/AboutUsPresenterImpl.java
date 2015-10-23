package com.payfever.presentation.fragment.about;

/**
 * Created by richi on 2015.10.23..
 */
public class AboutUsPresenterImpl implements AboutUsPresenter {

    private AboutUsView mView;

    @Override
    public void initialize() {
        mView.initTitle();
        mView.showContent();
    }

    @Override
    public void setView(AboutUsView _view) {
        mView = _view;
    }
}
