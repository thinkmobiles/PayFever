package com.payfever.presentation.fragment.chat;

import android.os.Bundle;
import android.util.Log;

import com.payfever.data.model.ChatModel;
import com.payfever.data.model.ContactModel;
import com.payfever.data.model.response.GetChatListResponse;
import com.payfever.domain.basics.BaseInteractor;
import com.payfever.domain.interactors.chat.ChatListInteractor;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

import rx.Subscriber;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatListPresenterImpl implements ChatListPresenter {

    private ChatView mChatView;
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
    public void setView(ChatView _view) {
        mChatView = _view;
        mChatView.setTitle();
    }

    @Override
    public void downloadData() {
        if (chatModelList == null) {
            mChatView.showProgress();
            chatListInteractor.executeGET(new SubscriberListChat());
        }
    }

    @Override
    public void onChatItemClick() {
        mChatView.onChatItemClick();
    }

    private class SubscriberListChat extends Subscriber<GetChatListResponse> {
        @Override
        public void onCompleted() {
            mChatView.setData(chatModelList);
            mChatView.hideProgress();
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
