package com.payfever.data.services.chat;

import com.parse.ParseException;
import com.payfever.data.api.chat_api.ChatApi;
import com.payfever.data.api.chat_api.ChatApiImpl;
import com.payfever.data.api.chat_api.TestChatApiImpl;
import com.payfever.data.model.network.NetworkResponse;
import com.payfever.data.model.response.GetChatListResponse;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatServiceImpl implements ChatService {

    private ChatApi chatApi;

    public ChatServiceImpl() {
//        chatApi = new ChatApiImpl();
        chatApi = new TestChatApiImpl();
    }

    @Override
    public Observable<GetChatListResponse> getChatData() {
        return Observable.create(new Observable.OnSubscribe<GetChatListResponse>() {
            @Override
            public void call(Subscriber<? super GetChatListResponse> subscriber) {
                try {
                    GetChatListResponse data = chatApi.getChatListData();
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (ParseException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
