package com.genesis.http;

import com.genesis.entity.ResponseBean;
import com.genesis.exception.ConnectionException;
import com.genesis.exception.MapperException;
import com.genesis.httpInterface.ObjectMapper;
import com.genesis.httpInterface.RequestInterface;
import com.genesis.httpInterface.ResponseCatchData;
import com.genesis.utils.HttpConfig;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public abstract class ObjectHttpInterface implements RequestInterface {


    @Override
    public <T> ResponseBean<T> get(String url, Map<String, String> headers, Class<T> responseType, Map<String, String> value) {
        return null;
    }

    @Override
    public <T> ResponseBean<T> get(String url, Class<T> responseType, Map<String, String> value) {
        return null;
    }

    @Override
    public <T> ResponseBean<T> post(String url, Class<T> responseType, String content, MediaType mediaType) throws ConnectionException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, objectMapper().toString(content))).build();
        ResponseCatchData<ResponseBean<T>> responseCatchData = new EntityCatch<>(objectMapper(), responseType);
        return handleResponse(null, request, responseCatchData);
    }

    @Override
    public <T> ResponseBean<T> post(String url, Map<String, String> headers, Class<T> responseType, String content, MediaType mediaType) {
        return null;
    }

    private <T> T handleResponse(HttpConfig httpConfig, Request request, ResponseCatchData<T> responseCatchData) throws ConnectionException {
        if (httpConfig != null) {
            httpHelper(httpConfig);
        }
        Response response = null;
        try {
            response = httpHelper().execute(request);
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
        if (response.isSuccessful()) {
            try {
                return responseCatchData.catchDate(request,response,callBack());
            } catch (IOException e) {
                throw new ConnectionException("返回结果失败: ", e);
            } catch (MapperException e) {
                throw new MapperException(String.format("转换失败: %s", e.getMessage()), e);
            } catch (Throwable e) {
                throw new ConnectionException(e);
            }
        }
        //TODO
        return null;
    }
}
