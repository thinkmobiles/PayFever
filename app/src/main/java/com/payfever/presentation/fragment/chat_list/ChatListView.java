package com.payfever.presentation.fragment.chat_list;


import com.payfever.data.model.ChatModel;
import com.payfever.presentation.basics.BaseView;

import java.util.List;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public interface ChatListView extends BaseView<List<ChatModel>> {
    void onChatItemClick();
    void setTitle();
}
