package com.payfever.data.services.chat_list;

import com.parse.ParseException;
import com.payfever.data.api.chat_list_api.ChatListApi;
import com.payfever.data.api.chat_list_api.TestChatListApiImpl;
import com.payfever.data.model.response.GetChatListResponse;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatListServiceImpl implements ChatListService {

    private ChatListApi chatListApi;

    public ChatListServiceImpl() {
//        chatListApi = new ChatListApiImpl();
        chatListApi = new TestChatListApiImpl();
    }

    @Override
    public Observable<GetChatListResponse> getChatData() {
        return Observable.create(new Observable.OnSubscribe<GetChatListResponse>() {
            @Override
            public void call(Subscriber<? super GetChatListResponse> subscriber) {
                try {
                    GetChatListResponse data = chatListApi.getChatListData();
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
