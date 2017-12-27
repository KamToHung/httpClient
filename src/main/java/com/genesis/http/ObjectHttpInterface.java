package com.genesis.http;

import com.genesis.entity.ResponseBean;
import com.genesis.httpInterface.RequestInterface;
import com.genesis.utils.HttpConfig;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;

public abstract class ObjectHttpInterface implements RequestInterface{

    @Override
    public <T> ResponseBean<T> get(String url, Map<String, String> headers, Class<T> responseType, Map<String, String> value) {
        return null;
    }

    @Override
    public <T> ResponseBean<T> get(String url, Class<T> responseType, Map<String, String> value) {
        return null;
    }

    @Override
    public <T> ResponseBean<T> post(String url, Class<T> responseType, String content, MediaType mediaType) {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();

        return null;
    }

    @Override
    public <T> ResponseBean<T> post(String url, Map<String, String> headers, Class<T> responseType, String content, MediaType mediaType) {
        return null;
    }

    private <T> T handleResponse(HttpConfig httpConfig, Request request, ResponseBean<T> responseBean) {
        return null;
    }
}
