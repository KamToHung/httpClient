package com.genesis.exception;

/**
 * 转换异常处理
 */
public class MapperException extends ConnectionException {

    private static final long serialVersionUID = -2065550267302834598L;

    public MapperException() {
        super();
    }

    public MapperException(String msg) {
        super(msg);
    }

    public MapperException(Throwable nestedThrowable) {
        super(nestedThrowable);
    }

    public MapperException(String msg, Throwable nestedThrowable) {
        super(msg, nestedThrowable);
    }
}
