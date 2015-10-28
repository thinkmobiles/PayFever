package com.payfever.data.services.chat;

import com.parse.ParseException;
import com.payfever.data.api.chat_api.ChatApi;
import com.payfever.data.api.chat_api.TestChatApiImpl;
import com.payfever.data.model.response.GetChatResponse;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public final class ChatServiceImpl implements ChatService {

    private ChatApi chatApi;

    public ChatServiceImpl() {
//        chatApi = new ChatApiImpl();
        chatApi = new TestChatApiImpl();
    }

    @Override
    public Observable<GetChatResponse> getChatData() {
        return Observable.create(new Observable.OnSubscribe<GetChatResponse>() {
            @Override
            public void call(Subscriber<? super GetChatResponse> subscriber) {
                try {
                    GetChatResponse data = chatApi.getChatData();
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
