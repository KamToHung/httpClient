package com.genesis.templet.xml;

import com.genesis.http.HttpHelper;
import com.genesis.http.ObjectHttpInterface;
import com.genesis.httpInterface.ObjectMapper;
import com.genesis.httpInterface.ResponseCallBack;
import com.genesis.utils.HttpConfig;

public class XmlHelper extends ObjectHttpInterface {

    private HttpHelper httpHelper;

    private ObjectMapper objectMapper;

    private ResponseCallBack responseCallBack;

    public XmlHelper(HttpConfig httpConfig, ObjectMapper objectMapper, ResponseCallBack responseCallBack) {
        if (httpConfig != null) {
            this.httpHelper = new HttpHelper(httpConfig);
        } else {
            this.httpHelper = new HttpHelper();
        }
        this.objectMapper = objectMapper;
        this.responseCallBack = responseCallBack;
    }

    public XmlHelper(HttpConfig httpConfig, ObjectMapper objectMapper) {
        this(httpConfig, objectMapper, null);
    }

    public XmlHelper(ObjectMapper objectMapper, ResponseCallBack responseCallBack) {
        this(null, objectMapper, responseCallBack);
    }

    public XmlHelper(HttpConfig httpConfig, ResponseCallBack responseCallBack) {
        this(httpConfig, ObjectMapper.DEFAULT_MAPPER_XML, responseCallBack);
    }

    public XmlHelper(HttpConfig httpConfig) {
        this(httpConfig, ObjectMapper.DEFAULT_MAPPER_XML, null);
    }

    @Override
    public ObjectMapper objectMapper() {
        return this.objectMapper;
    }

    @Override
    public HttpHelper httpHelper() {
        return this.httpHelper;
    }

    @Override
    public ResponseCallBack callBack() {
        return this.responseCallBack;
    }

    @Override
    public HttpHelper httpHelper(HttpConfig config) {
        return new HttpHelper(config);
    }
}
