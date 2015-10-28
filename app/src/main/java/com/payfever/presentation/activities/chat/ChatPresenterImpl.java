package com.payfever.presentation.activities.chat;

import android.os.Bundle;
import android.util.Log;

import com.payfever.data.model.ChatModel;
import com.payfever.data.model.response.GetChatResponse;
import com.payfever.domain.interactors.chat.ChatInteractor;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

import rx.Subscriber;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public final class ChatPresenterImpl implements ChatPresenter {

    private ChatView chatView;
    private List<ChatModel> chatModelList;
    private ChatInteractor chatInteractor;

    public ChatPresenterImpl() {
        chatInteractor = new ChatInteractor(PayFeverApplication.getApplication().getBackgroundHandler());
    }

    @Override
    public void initialize(Bundle _savedInstanceState) {

    }

    @Override
    public void onPause() {
        chatInteractor.unSubscribe();
    }

    @Override
    public void setView(ChatView _view) {
        chatView = _view;
        chatView.setTitle();
    }

    @Override
    public void downloadData() {
        if (chatModelList == null) {
            chatView.showProgress();
            chatInteractor.executeGET(new SubscriberChat());
        }
    }

    @Override
    public void sendMessage() {

    }

    private class SubscriberChat extends Subscriber<GetChatResponse> {
        @Override
        public void onCompleted() {
            chatView.setData(chatModelList);
            chatView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Subscriber", e.getMessage());
        }

        @Override
        public void onNext(GetChatResponse contactModels) {
            chatModelList = contactModels.getChatModelList();
        }
    }
}
