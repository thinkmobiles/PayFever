package com.payfever.data.api.chat_list_api;

import com.parse.ParseException;
import com.payfever.data.model.ChatListModel;
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
            Thread.currentThread().sleep(1000);
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

    private List<ChatListModel> getChatList() {
        List<ChatListModel> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ChatListModel chatListModel = new ChatListModel();
            chatListModel.setUserName("Linda Corel");
            chatListModel.setDecription("It is actually pritty good! My special project");
            chatListModel.setPushChannel("push channel: " + i);
            list.add(chatListModel);
        }

        return list;
    }
}
