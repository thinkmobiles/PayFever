package com.payfever.presentation.fragment.network;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.network.NetworkResponse;
import com.payfever.data.model.network.Statistic;
import com.payfever.presentation.activities.contact.ContactActivity;
import com.payfever.presentation.basics.BaseFABFragment;
import com.payfever.presentation.global.Constants;

/**
 * Created by richi on 2015.10.21..
 */
public class MyNetworkFragment extends BaseFABFragment
        implements NetworkView, AdapterView.OnItemClickListener, View.OnClickListener {

    public static final int REQUEST_CODE_INVITE = 1;

    private ListView lvUsers;
    private View mHeaderView;
    private TextView tvDirectSentOut, tvPending, tvExpired,
            tvTotal, tvFirstLevel, tvNetworkLevel;

    private NetworkPresenter mPresenter;
    private NetworkAdapter mAdapter;

    private NetworkResponse mData;

    public static BaseFABFragment newInstance() {
        Bundle args = new Bundle();

        BaseFABFragment fabFragment = new MyNetworkFragment();
        fabFragment.setArguments(args);

        return fabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_network);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initHeaderView();
        findUI();
        addHeaderToList();
        initListeners();
        initPresenter(savedInstanceState);
        initAdapter();
    }

    private void initHeaderView() {
        mHeaderView = LayoutInflater.from(mActivity).inflate(R.layout.header_my_network, null, false);

        tvDirectSentOut     = (TextView) mHeaderView.findViewById(R.id.tvDirectSentOut_HMN);
        tvPending           = (TextView) mHeaderView.findViewById(R.id.tvPending_HMN);
        tvExpired           = (TextView) mHeaderView.findViewById(R.id.tvExpired_HMN);
        tvTotal             = (TextView) mHeaderView.findViewById(R.id.tvTotal_HMN);
        tvFirstLevel        = (TextView) mHeaderView.findViewById(R.id.tvFirstLevel_HMN);
        tvNetworkLevel      = (TextView) mHeaderView.findViewById(R.id.tvNetworkLevel_HMN);
    }

    private void findUI() {
        lvUsers     = $(R.id.lvUsers_FMN);
    }

    private void addHeaderToList() {
        lvUsers.addHeaderView(mHeaderView);
    }

    private void initListeners() {
        lvUsers.setOnItemClickListener(this);
        getFabController().setOnClickListener(this);
    }

    private void initPresenter(Bundle savedInstanceState) {
        mPresenter = new NetworkPresenterImpl();
        mPresenter.setView(this);
        mPresenter.initialize(savedInstanceState);
    }

    private void initAdapter() {
        mAdapter = new NetworkAdapter();
    }

    @Override
    public void showProgress() {
        getLoadingManager().showProgress();
    }

    @Override
    public void hideProgress() {
        getLoadingManager().hideProgress();
    }

    @Override
    public void setTitle() {
        getToolbarController().setTitle(mActivity.getString(R.string.network_fragment_title_HMN));
    }

    @Override
    public void setData(NetworkResponse _data) {
        mData = _data;
        mAdapter.setData(_data.getmNetworkStatistic().getmUsers());
        lvUsers.setAdapter(mAdapter);
    }

    @Override
    public void setStaticData(Statistic _statistic) {
        tvDirectSentOut.setText(_statistic.getmSentOut());
        tvPending.setText(_statistic.getmPending());
        tvExpired.setText(_statistic.getmExpired());
        tvTotal.setText(_statistic.getmTotalNetworkAccepts());
        tvFirstLevel.setText(_statistic.getmFirstLevelAccepts());
        tvNetworkLevel.setText(_statistic.getmNetworkLevelAccepts());
    }

    @Override
    public void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mPresenter.onResume();
        super.onResume();
    }

    @Override
    public void openInviteContacts() {
        Intent intent = ContactActivity.getCallingIntent(mActivity);
        intent.putExtra(Constants.FROM_NETWORK_FRAGMENT, true);
        startActivityForResult(intent, REQUEST_CODE_INVITE);
    }

    @Override
    public void showServerError(Throwable e) {
        getLoadingManager().showNetworkExceptionMessage(e);
    }

    @Override
    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int _position, long id) {
        mPresenter.onItemClicked(_position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_AM:
                mPresenter.fabClicked();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(resultCode, requestCode, data);
    }

    @Override
    public void retryRequest() {
        mPresenter.downloadData();
    }
}
