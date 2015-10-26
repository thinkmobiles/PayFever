package com.payfever.data.api.chat_api;

import com.parse.ParseException;
import com.payfever.data.model.response.GetChatListResponse;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public interface ChatApi {
    GetChatListResponse getChatListData() throws ParseException;
}
