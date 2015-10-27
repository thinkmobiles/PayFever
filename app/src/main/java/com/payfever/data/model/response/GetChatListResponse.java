package com.payfever.data.model.response;

import com.payfever.data.model.ChatListModel;

import java.util.List;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class GetChatListResponse {

    private List<ChatListModel> models;

    public List<ChatListModel> getModels() {
        return models;
    }

    public void setModels(List<ChatListModel> models) {
        this.models = models;
    }
}
