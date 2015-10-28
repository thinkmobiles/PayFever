package com.payfever.presentation.fragment.chat_list;

import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public interface ChatListPresenter extends BasePresenter<ChatListView> {
    void downloadData();
    void onChatItemClick();
}
