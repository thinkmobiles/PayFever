package com.payfever.data.services;

import com.payfever.data.services.chat.ChatService;
import com.payfever.data.services.chat.ChatServiceImpl;
import com.payfever.data.services.chat_list.ChatListService;
import com.payfever.data.services.chat_list.ChatListServiceImpl;
import com.payfever.data.services.network.NetworkService;
import com.payfever.data.services.network.NetworkServiceImpl;
import com.payfever.data.services.registration.RegisterService;
import com.payfever.data.services.registration.RegisterServiceImpl;
import com.payfever.data.services.contacts.ContactServiceImpl;
import com.payfever.data.services.contacts.ContactService;

/**
 * Created by richi on 2015.10.19..
 */
public class ServiceProvider {

    private static ServiceProvider sInstance;

    private ContactService mContactService;
    private RegisterService mRegisterService;
    private NetworkService mNetworkService;
    private ChatListService mChatListService;
    private ChatService mChatService;

    private ServiceProvider() {
        mContactService = new ContactServiceImpl();
        mRegisterService = new RegisterServiceImpl();
        mNetworkService = new NetworkServiceImpl();
        mChatListService = new ChatListServiceImpl();
        mChatService = new ChatServiceImpl();
    }

    public static ServiceProvider getInstance() {
        if (sInstance == null)
            sInstance = new ServiceProvider();

        return sInstance;
    }

    public ContactService getContactService() {
        return mContactService;
    }

    public RegisterService getRegisterService() {
        return mRegisterService;
    }

    public NetworkService getNetworkService() {
        return mNetworkService;
    }

    public ChatListService getChatListService() {
        return mChatListService;
    }

    public ChatService getChatService() {
        return mChatService;
    }
}
