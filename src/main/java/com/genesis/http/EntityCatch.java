package com.genesis.http;

import com.genesis.entity.ResponseBean;
import com.genesis.exception.MapperException;
import com.genesis.httpinterface.ObjectMapper;
import com.genesis.httpinterface.ResponseCallBack;
import com.genesis.httpinterface.ResponseCatchData;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Terry
 */
public class EntityCatch<T> implements ResponseCatchData<ResponseBean<T>> {

    private ObjectMapper objectMapper;

    private Class<T> cls;

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
