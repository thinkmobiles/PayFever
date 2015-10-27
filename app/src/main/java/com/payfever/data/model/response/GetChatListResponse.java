package com.payfever.data.model.response;

import com.payfever.data.model.ChatModel;

import java.util.List;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class GetChatListResponse {

    private List<ChatModel> models;

    public List<ChatModel> getModels() {
        return models;
    }

    public void setModels(List<ChatModel> models) {
        this.models = models;
    }
}
