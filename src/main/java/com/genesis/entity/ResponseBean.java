package com.genesis.entity;

import okhttp3.Request;
import okhttp3.Response;

public class ResponseBean<T> {

    /**
     * 响应
     */
    private final Response response;

    /**
     * 请求
     */
    private final Request request;

    /**
     * 响应内容
     */
    private final String responseBody;

    /**
     * 对象
     */
    private T value;


    public Response getResponse() {
        return response;
    }

    public Request getRequest() {
        return request;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public T getValue() {
        return value;
    }

    public ResponseBean(Request request, Response response, T value, String responseBody) {
        this.response = response;
        this.request = request;
        this.responseBody = responseBody;
        this.value = value;
    }
}
