package com.payfever.data.api.chat_api;

import com.parse.ParseException;
import com.payfever.data.model.ChatListModel;
import com.payfever.data.model.ChatModel;
import com.payfever.data.model.response.GetChatListResponse;
import com.payfever.data.model.response.GetChatResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public final class TestChatApiImpl implements ChatApi {

    @Override
    public GetChatResponse getChatData() throws ParseException {
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return generateData();
    }

    private GetChatResponse generateData() {
        GetChatResponse getChatListResponse = new GetChatResponse();
        getChatListResponse.setChatModelList(getChatList());
        return getChatListResponse;
    }

    private List<ChatModel> getChatList() {
        List<ChatModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChatModel chatModel = new ChatModel();
            chatModel.setUserName("Semi");
            chatModel.setDescription("Decription: " + i);
            chatModel.setPushChannel("push channel: " + i);
            chatModel.setDate("date: " + i);
            list.add(chatModel);
        }

        for (int i = 10; i < 20; i++) {
            ChatModel chatModel = new ChatModel();
            chatModel.setUserName("Sem");
            chatModel.setDescription("Decription: " + i);
            chatModel.setPushChannel("push channel: " + i);
            chatModel.setDate("date: " + i);
            list.add(chatModel);
        }

        return list;
    }
}
