package com.genesis.http;

import com.genesis.entity.ResponseBean;
import com.genesis.exception.MapperException;
import com.genesis.httpInterface.ObjectMapper;
import com.genesis.httpInterface.ResponseCallBack;
import com.genesis.httpInterface.ResponseCatchData;
import okhttp3.Request;
import okhttp3.Response;

public class EntityCatch<T> implements ResponseCatchData<ResponseBean<T>>{

    private ObjectMapper objectMapper;

    private Class<T> cls;


    @Override
    public ResponseBean<T> catchDate(Request request, Response response, ResponseCallBack responseCallBack) throws MapperException {
        return null;
    }
}
