package com.payfever.presentation.fragment.set_ringtones;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.presentation.basics.BaseFABFragment;
import com.payfever.presentation.fragment.chat_list.ChatListAdapter;
import com.payfever.presentation.fragment.chat_list.ChatListPresenterImpl;

import java.io.IOException;
import java.util.List;

/**
 * Created by richi on 2015.10.28..
 */
public class RingtonesFragment extends BaseFABFragment implements RingtonesView, OnPayToneItemListener {

    private ListView lvRingtones;
    private RingtonesAdapter mAdapter;
    private TextView tvEmptyList;
    private RingtonesPresenter mPresenter;
    private ImageView ivPlayTone;
    private TextView tvSetRingTone;
    private ProgressDialog mProgressDialog;
    private MediaPlayer mMdiaPlayer;

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
        initPresenter();
        findUI();
        initObjects();
        initListeners();
        getToolbarController().setTitle("Ringtone");
        mPresenter.initialize(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.downloadRingtones();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stopPlaying();
        mPresenter.onStop();
    }

    private void initPresenter() {
        mPresenter = new RingtonesPresenterImpl();
        mPresenter.setView(this);
    }

    private void findUI() {
        lvRingtones = $(R.id.lvRingtones_FR);
        tvEmptyList = $(R.id.tvEmptyList_FR);
    }

    private void initObjects() {
        mAdapter = new RingtonesAdapter();
        mMdiaPlayer = new MediaPlayer();
    }

    private void initListeners() {
        mAdapter.setOnPayToneItemListener(this);
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
    public void setData(List<Ringtone> _data) {
        mAdapter.setRingtones(_data);
        lvRingtones.setAdapter(mAdapter);
        lvRingtones.setEmptyView(tvEmptyList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void playRingtone(Ringtone _ringtone) {
        mPresenter.playRingtone(_ringtone);
    }

    @Override
    public void setPayTone(String _url, String _name) {
        mPresenter.setPayTone(_url, _name);
    }

    @Override
    public void showDownloadProgress() {
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setTitle("Downloading Ringtone ...");
        mProgressDialog.setMessage("Download in progress ...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgress(0);
        mProgressDialog.setMax(100);
        mProgressDialog.show();
    }

    @Override
    public void updateProgress(Integer _value) {
        mProgressDialog.setProgress(_value);
    }

    @Override
    public void dismiss() {
        mProgressDialog.dismiss();
    }

    @Override
    public void notifyData() {
        mAdapter.notifyDataSetChanged();
    }
}
