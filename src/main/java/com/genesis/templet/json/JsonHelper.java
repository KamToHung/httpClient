package com.genesis.templet.json;

import com.genesis.http.HttpHelper;
import com.genesis.http.ObjectHttpInterface;
import com.genesis.httpInterface.ObjectMapper;
import com.genesis.httpInterface.RequestInterface;
import com.genesis.httpInterface.ResponseCallBack;
import com.genesis.utils.HttpConfig;

public class JsonHelper extends ObjectHttpInterface {


    private HttpHelper httpHelper;

    private ResponseCallBack responseCallBack;

    private ObjectMapper objectMapper;

    public static RequestInterface getInstance() {
        return new JsonHelper();
    }

    public JsonHelper() {
        this.httpHelper = new HttpHelper();
        this.objectMapper = ObjectMapper.DEFAULT_MAPPER_JSON;
        this.responseCallBack = null;
    }

    public JsonHelper(HttpConfig httpConfig, ResponseCallBack responseCallBack, ObjectMapper objectMapper) {
        if (httpConfig != null) {
            this.httpHelper = new HttpHelper(httpConfig);
        } else {
            this.httpHelper = new HttpHelper();
        }
        this.responseCallBack = responseCallBack;
        this.objectMapper = objectMapper;
    }

    public JsonHelper(HttpConfig httpConfig, ResponseCallBack responseCallBack) {
        this(httpConfig, responseCallBack, ObjectMapper.DEFAULT_MAPPER_JSON);
    }

    public JsonHelper(HttpConfig httpConfig) {
        this(httpConfig, null, ObjectMapper.DEFAULT_MAPPER_JSON);
    }

    public JsonHelper(ObjectMapper objectMapper) {
        this(null, null, objectMapper);
    }

    public JsonHelper(ResponseCallBack responseCallBack, ObjectMapper objectMapper) {
        this(null, responseCallBack, objectMapper);
    }

    @Override
    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    @Override
    public HttpHelper httpHelper() {
        return httpHelper;
    }

    @Override
    public ResponseCallBack callBack() {
        return responseCallBack;
    }

    @Override
    public HttpHelper httpHelper(HttpConfig config) {
        return new HttpHelper(config);
    }
}
