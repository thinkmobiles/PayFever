package com.payfever.presentation.fragment.about;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.payfever.R;
import com.payfever.presentation.basics.BaseFABFragment;

/**
 * Created by richi on 2015.10.23..
 */
public class AboutUsFragment extends BaseFABFragment implements AboutUsView {

    private AboutUsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);

        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new AboutUsPresenterImpl();
        mPresenter.setView(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.initialize();
    }

    @Override
    public void initTitle() {
        getToolbarController().setTitle(mActivity.getString(R.string.about_us_title_AU));
    }

    @Override
    public void showContent() {
        getLoadingManager().hideProgress();
    }
}
