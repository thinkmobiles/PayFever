package com.payfever.data.model.response;

import com.payfever.data.model.ChatModel;

import java.util.List;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public class GetChatResponse {
    private List<ChatModel> chatModelList;

    public List<ChatModel> getChatModelList() {
        return chatModelList;
    }

    public void setChatModelList(List<ChatModel> chatModelList) {
        this.chatModelList = chatModelList;
    }
}
