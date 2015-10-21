package com.payfever.presentation.fragment.network;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.network.NetworkResponse;
import com.payfever.data.model.network.Statistic;
import com.payfever.presentation.basics.BaseFragment;

/**
 * Created by richi on 2015.10.21..
 */
public class MyNetworkFragment extends BaseFragment implements NetworkView, AdapterView.OnItemClickListener {

    private ListView lvUsers;
    private View mHeaderView;
    private TextView tvDirectSentOut, tvPending, tvExpired,
            tvTotal, tvFirstLevel, tvNetworkLevel;

    private NetworkPresenter mPresenter;
    private NetworkAdapter mAdapter;

    private NetworkResponse mData;

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
        initListeners();
        initPresenter(savedInstanceState);
        initAdapter();
    }

    private void initHeaderView() {
        mHeaderView = LayoutInflater.from(mActivity).inflate(R.layout.header_contact_list, null, false);

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

    private void initListeners() {
        lvUsers.setOnItemClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int _position, long id) {
        mPresenter.onItemClicked(_position);
    }
}
