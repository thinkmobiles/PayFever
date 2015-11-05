package com.payfever.presentation.activities.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.ChatModel;
import com.payfever.presentation.activities.NetworkExceptionActivity;
import com.payfever.presentation.basics.BaseActivity;

import java.util.List;


/**
 * Created by
 * mRogach on 27.10.2015.
 */

public final class ChatActivity extends NetworkExceptionActivity implements ChatView, View.OnClickListener {

    private ListView lvChat;
    private TextView tvSendMessage;
    private EditText etMessage;
    private ChatPresenter mChatPresenter;
    private ChatAdapter mChatAdapter;


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ChatActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findUI();
        setListeners();
        initPresenter();
        intiObjects();
        initToolbar();
        mChatPresenter.setView(this);
        mChatPresenter.initialize(savedInstanceState);
    }

    @Override
    public int getNetworkExceptionLayoutId() {
        return 0;
    }

    private void initToolbar() {
        setSupportActionBar(getToolbarController().getToolbar());
        getToolbarController().showBackButton(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mChatPresenter.downloadData();
    }

    private void findUI() {
        lvChat = $(R.id.lvChat_ACH);
        tvSendMessage = $(R.id.tvSendMessage_ACH);
        etMessage = $(R.id.etMessage_ACH);
    }

    private void setListeners() {
        tvSendMessage.setOnClickListener(this);
    }

    private void initPresenter() {
        mChatPresenter = new ChatPresenterImpl();
    }

    private void intiObjects() {
        mChatAdapter = new ChatAdapter();
    }

    @Override
    public int getToolbarId() {
        return R.id.toolbar_ACH;
    }

    @Override
    public int getContainerId() {
        return R.id.rlChatContainer_ACH;
    }

    @Override
    public int getProgressId() {
        return R.id.pbLoadingIndicator_ACH;
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
    public void setData(List<ChatModel> _data) {
        mChatAdapter.setChatModelList(_data);
        lvChat.setAdapter(mChatAdapter);
    }

    @Override
    public void onClick(View v) {
        mChatPresenter.sendMessage();
    }

    @Override
    public void setTitle() {
        getToolbarController().setTitle("Chat");
    }
}
