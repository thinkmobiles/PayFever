package com.payfever.data.api.chat_api;

import com.parse.ParseException;
import com.payfever.data.model.response.GetChatResponse;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public interface ChatApi {
    GetChatResponse getChatData() throws ParseException;
}
