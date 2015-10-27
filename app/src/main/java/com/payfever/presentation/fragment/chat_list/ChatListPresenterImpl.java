package com.payfever.presentation.fragment.chat_list;

import android.os.Bundle;
import android.util.Log;

import com.payfever.data.model.ChatModel;
import com.payfever.data.model.response.GetChatListResponse;
import com.payfever.domain.basics.BaseInteractor;
import com.payfever.domain.interactors.chat_list.ChatListInteractor;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

import rx.Subscriber;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatListPresenterImpl implements ChatListPresenter {

    private ChatListView mChatListView;
    private BaseInteractor chatListInteractor;
    private List<ChatModel> chatModelList;

    public ChatListPresenterImpl() {
        chatListInteractor = new ChatListInteractor(PayFeverApplication.getApplication().getBackgroundHandler());
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {

    }

    @Override
    public void onPause() {
        chatListInteractor.unSubscribe();
    }

    @Override
    public void setView(ChatListView _view) {
        mChatListView = _view;
        mChatListView.setTitle();
    }

    @Override
    public void downloadData() {
        if (chatModelList == null) {
            mChatListView.showProgress();
            chatListInteractor.executeGET(new SubscriberListChat());
        }
    }

    @Override
    public void onChatItemClick() {
        mChatListView.onChatItemClick();
    }

    private class SubscriberListChat extends Subscriber<GetChatListResponse> {
        @Override
        public void onCompleted() {
            mChatListView.setData(chatModelList);
            mChatListView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Subscriber", e.getMessage());
        }

        @Override
        public void onNext(GetChatListResponse chatModels) {
            chatModelList = chatModels.getModels();
        }
    }

}
