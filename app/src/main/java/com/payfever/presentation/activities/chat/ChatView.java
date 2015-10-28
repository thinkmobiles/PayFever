package com.payfever.presentation.activities.chat;

import com.payfever.data.model.ChatModel;
import com.payfever.presentation.basics.BaseView;

import java.util.List;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public interface ChatView extends BaseView<List<ChatModel>> {
    void setTitle();
}
