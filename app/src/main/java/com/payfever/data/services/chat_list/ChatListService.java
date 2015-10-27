package com.payfever.data.services.chat_list;

import com.payfever.data.model.response.GetChatListResponse;


import rx.Observable;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public interface ChatListService {
    Observable<GetChatListResponse> getChatData();
}
