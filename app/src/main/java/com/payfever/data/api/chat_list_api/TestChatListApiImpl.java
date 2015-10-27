package com.payfever.data.api.chat_list_api;

import com.parse.ParseException;
import com.payfever.data.model.ChatModel;
import com.payfever.data.model.response.GetChatListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public class TestChatListApiImpl implements ChatListApi {

    @Override
    public GetChatListResponse getChatListData() throws ParseException {
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return generateData();
    }

    private GetChatListResponse generateData() {
        GetChatListResponse getChatListResponse = new GetChatListResponse();
        getChatListResponse.setModels(getChatList());
        return getChatListResponse;
    }

    private List<ChatModel> getChatList() {
        List<ChatModel> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ChatModel chatModel = new ChatModel();
            chatModel.setUserName("Name: " + i);
            chatModel.setDecription("Decription: " + i);
            chatModel.setPushChannel("push channel: " + i);
            list.add(chatModel);
        }

        return list;
    }
}
