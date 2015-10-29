package com.payfever.presentation.fragment.chat_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.payfever.R;
import com.payfever.data.model.ChatListModel;
import com.payfever.presentation.activities.chat.ChatActivity;
import com.payfever.presentation.basics.BaseFABFragment;
import com.payfever.presentation.basics.BaseFragment;

import java.util.List;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatListFragment extends BaseFABFragment implements ChatListView, AdapterView.OnItemClickListener {

    private ListView lvChatList;
    private TextView tvEmptyList;
    private ChatListAdapter mAdapter;
    private ChatListPresenter chatListPresenter;

    public static BaseFABFragment newInstance() {
        Bundle args = new Bundle();

        BaseFABFragment fabFragment = new ChatListFragment();
        fabFragment.setArguments(args);

        return fabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findUI();
        initPresenter();
        initObjects();
        initListeners();
        chatListPresenter.initialize(savedInstanceState);
        chatListPresenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        chatListPresenter.downloadData();
    }

    private void initPresenter() {
        chatListPresenter = new ChatListPresenterImpl();
    }

    private void findUI() {
        lvChatList = $(R.id.lvChatList_FCL);
        tvEmptyList = $(R.id.tvEmptyList_FCL);
    }

    private void initObjects() {
        mAdapter = new ChatListAdapter();
    }

    private void initListeners() {
        lvChatList.setOnItemClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        chatListPresenter.onPause();
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
    public void setData(List<ChatListModel> _data) {
        mAdapter.setChatList(_data);
        lvChatList.setAdapter(mAdapter);
        lvChatList.setEmptyView(tvEmptyList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        chatListPresenter.onChatItemClick();
    }

    @Override
    public void onChatItemClick() {
        startActivity(ChatActivity.getCallingIntent(mActivity));
    }

    @Override
    public void setTitle() {
        getToolbarController().setTitle(getString(R.string.chat_list_title));
    }
}
