package com.payfever.presentation.activities.chat;

import com.payfever.presentation.basics.BasePresenter;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public interface ChatPresenter extends BasePresenter<ChatView> {
    void downloadData();
    void sendMessage();
}
