package com.payfever.presentation.terms_and_conditions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.presentation.activities.contact.ContactActivity;
import com.payfever.presentation.basics.BaseFragment;

/**
 * Created by
 * mRogach on 21.10.2015.
 */

public final class TermsConditionsFragment extends BaseFragment implements View.OnClickListener, TermsConditionsView {

    private TextView tvAccept;
    private TermsConditionsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_terms_and_conditions);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initPresenter();
        findUI();
        initListeners();
        mPresenter.initialize(savedInstanceState);
        mPresenter.setView(this);
    }

    private void initPresenter() {
        mPresenter = new TermsConditionsPresenterImpl();
    }

    private void findUI() {
        tvAccept = $(R.id.tvAcceptTC_FTAC);
    }

    private void initListeners() {
        tvAccept.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAcceptTC_FTAC:
                mPresenter.accept();
                break;
        }
    }

    @Override
    public void accept() {
        startActivity(ContactActivity.getCallingIntent(mActivity));
    }

    @Override
    public void setTitle() {
        getToolbarController().setTitle(getString(R.string.terms_and_conditions));
    }
}
