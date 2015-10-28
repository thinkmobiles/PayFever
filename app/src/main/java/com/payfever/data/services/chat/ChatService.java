package com.payfever.data.services.chat;

import com.payfever.data.model.response.GetChatResponse;

import rx.Observable;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public interface ChatService {
    Observable<GetChatResponse> getChatData();
}
