package com.genesis.http;

import com.genesis.exception.MapperException;
import com.genesis.httpInterface.ObjectMapper;
import com.genesis.httpInterface.ResponseCallBack;
import com.genesis.httpInterface.ResponseCatchData;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Description:
 * @author: Terry
 * @create 2018-02-27 10:30
 */
public class ObjectCatch<T> implements ResponseCatchData<T> {

    private ObjectMapper objectMapper;

    private Class<T> responseType;

    public ObjectCatch(ObjectMapper objectMapper,Class<T> responseType) {
        this.objectMapper = objectMapper;
        this.responseType = responseType;
    }

    @Override
    public T catchDate(Request request, Response response, ResponseCallBack responseCallBack) throws MapperException, IOException {
        String resp = response.body().string();
        if (responseCallBack != null) {
            resp = responseCallBack.dealData(resp);
        }
        if (responseType == String.class) {
            return (T) resp;
        }
        return objectMapper.parse(resp, responseType);
    }
}
