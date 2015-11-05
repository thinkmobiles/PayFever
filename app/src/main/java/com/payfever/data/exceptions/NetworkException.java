package com.payfever.data.exceptions;

/**
 * Created by
 * mRogach on 05.11.2015.
 */

public final class NetworkException extends Exception {

    public NetworkException() {
        super("No internet connection");
    }

    public NetworkException(Throwable throwable) {
        super("No internet connection", throwable);
    }
}
