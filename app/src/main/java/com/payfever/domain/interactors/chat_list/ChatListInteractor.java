package com.payfever.domain.interactors.chat_list;

import android.os.Handler;

import com.payfever.data.services.ServiceProvider;
import com.payfever.domain.basics.BaseInteractor;

import rx.Observable;

/**
 * Created by
 * mRogach on 26.10.2015.
 */

public final class ChatListInteractor extends BaseInteractor {

    public ChatListInteractor(Handler _handler) {
        super(_handler);
    }

    @Override
    protected Observable buildGetObserver() {
        return ServiceProvider.getInstance().getChatService().getChatData();
    }
}
