package com.genesis.exception;

import java.io.Serializable;

public class ConnectionException extends Exception {

    private static final long serialVersionUID = -8863994063415020267L;

    public ConnectionException() {
        super();
    }

    public ConnectionException(String msg) {
        super(msg);
    }

    public ConnectionException(Throwable nestedThrowable) {
        super(nestedThrowable);
    }

    public ConnectionException(String msg, Throwable nestedThrowable) {
        super(msg, nestedThrowable);
    }
}
