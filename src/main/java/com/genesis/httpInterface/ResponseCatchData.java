package com.genesis.httpInterface;


import com.genesis.exception.MapperException;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public interface ResponseCatchData<T> {

    /**
     *获取response的数据
     * @param request
     * @param response
     * @param responseCallBack
     * @return
     * @throws MapperException
     */
    T catchDate(Request request, Response response, ResponseCallBack responseCallBack) throws MapperException, IOException;
}
