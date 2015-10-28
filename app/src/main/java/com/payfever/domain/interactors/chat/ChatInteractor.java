package com.payfever.domain.interactors.chat;

import android.os.Handler;

import com.payfever.data.services.ServiceProvider;
import com.payfever.domain.basics.BaseInteractor;

import rx.Observable;

/**
 * Created by
 * mRogach on 27.10.2015.
 */

public final class ChatInteractor extends BaseInteractor {

    public ChatInteractor(Handler _handler) {
        super(_handler);
    }

    @Override
    protected Observable buildGetObserver() {
        return ServiceProvider.getInstance().getChatService().getChatData();
    }
}
