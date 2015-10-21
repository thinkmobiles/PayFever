package com.payfever.presentation.fragment.network;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.payfever.R;
import com.payfever.data.model.network.NetworkResponse;
import com.payfever.presentation.basics.BaseFragment;

/**
 * Created by richi on 2015.10.21..
 */
public class MyNetworkFragment extends BaseFragment implements NetworkView, AdapterView.OnItemClickListener {

    private ListView lvUsers;

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

        findUI();
        initListeners();
        initPresenter(savedInstanceState);
        initAdapter();
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
    public void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int _position, long id) {
        mPresenter.onItemClicked(_position);
    }
}
