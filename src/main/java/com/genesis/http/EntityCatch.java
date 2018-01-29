package com.genesis.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.genesis.entity.ResponseBean;
import com.genesis.exception.MapperException;
import com.genesis.httpInterface.ObjectMapper;
import com.genesis.httpInterface.ResponseCallBack;
import com.genesis.httpInterface.ResponseCatchData;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class EntityCatch<T> implements ResponseCatchData<ResponseBean<T>> {

    private ObjectMapper objectMapper;

    private Class<T> cls;

    private TypeReference<T> typeRef;

    public EntityCatch(ObjectMapper objectMapper, Class<T> cls) {
        this.objectMapper = objectMapper;
        this.cls = cls;
    }

    @Override
    public ResponseBean<T> catchDate(Request request, Response response, ResponseCallBack responseCallBack) throws MapperException, IOException {

        String responseData = response.body().string();
        if (responseCallBack != null) {
            responseData = responseCallBack.dealData(responseData);
        }
        if (cls == String.class) {
            return new ResponseBean<>(request, response, null, responseData);
        }
        return new ResponseBean<>(request, response, objectMapper.parse(responseData, cls), responseData);
    }

}
