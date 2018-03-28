package com.genesis.http;

import com.genesis.entity.ResponseBean;
import com.genesis.exception.ConnectionException;
import com.genesis.exception.MapperException;
import com.genesis.httpinterface.RequestInterface;
import com.genesis.httpinterface.ResponseCatchData;
import com.genesis.utils.HandleUrlUtils;
import com.genesis.utils.HttpConfig;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author Terry
 */
public abstract class AbstractObjectHttpInterface implements RequestInterface {

    @Override
    public <T> ResponseBean<T> getEntity(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> value) throws ConnectionException {
        Request request = new Request.Builder().headers(Headers.of(headers)).url(HandleUrlUtils.splitUrl(url, value)).build();
        ResponseCatchData<ResponseBean<T>> responseCatchData = new EntityCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    @Override
    public <T> ResponseBean<T> getEntity(String url, Class<T> responseType, Map<String, ?> value) throws ConnectionException {
        Request request = new Request.Builder().url(HandleUrlUtils.splitUrl(url, value)).build();
        ResponseCatchData<ResponseBean<T>> responseCatchData = new EntityCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    @Override
    public <T> ResponseBean<T> postEntity(String url, Class<T> responseType, String content, MediaType mediaType) throws ConnectionException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
        ResponseCatchData<ResponseBean<T>> responseCatchData = new EntityCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    @Override
    public <T> ResponseBean<T> postEntity(String url, Map<String, String> headers, Class<T> responseType, String content, MediaType mediaType) throws ConnectionException {
        Request request = new Request.Builder().headers(Headers.of(headers)).url(url).post(RequestBody.create(mediaType, content)).build();
        ResponseCatchData<ResponseBean<T>> responseCatchData = new EntityCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    @Override
    public <T> T getObject(String url, Class<T> responseType, Map<String, ?> value) throws ConnectionException {
        Request request = new Request.Builder().url(HandleUrlUtils.splitUrl(url, value)).build();
        ResponseCatchData<T> responseCatchData = new ObjectCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    @Override
    public <T> T getObject(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> value) throws ConnectionException {
        Request request = new Request.Builder().headers(Headers.of(headers)).url(HandleUrlUtils.splitUrl(url, value)).build();
        ResponseCatchData<T> responseCatchData = new ObjectCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    @Override
    public <T> T postObject(String url, Class<T> responseType, String content, MediaType mediaType) throws ConnectionException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, content)).build();
        ResponseCatchData<T> responseCatchData = new ObjectCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    @Override
    public <T> T postObject(String url, Map<String, String> headers, Class<T> responseType, String content, MediaType mediaType) throws ConnectionException {
        Request request = new Request.Builder().headers(Headers.of(headers)).url(url).post(RequestBody.create(mediaType, content)).build();
        ResponseCatchData<T> responseCatchData = new ObjectCatch<>(objectMapper(), responseType);
        return handleResponse(request, responseCatchData);
    }

    /**
     * deal with response
     * config will be initialized after the RequestInterface is initialized
     * @param httpConfig
     * @param request
     * @param responseCatchData
     * @param <T>
     * @return
     * @throws ConnectionException
     */
    @Deprecated
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
                return responseCatchData.catchDate(request, response, callBack());
            } catch (IOException e) {
                throw new ConnectionException("返回结果失败: ", e);
            } catch (MapperException e) {
                throw new MapperException(String.format("转换失败: %s", e.getMessage()), e);
            }
        }
        String responseData = null;
        try {
            responseData = response.body().string();
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
        throw new ConnectionException("Http Code is : " + response.code() + " and response is :" + responseData);
    }

    /**
     * deal with response
     * @param request request
     * @param responseCatchData response‘s data
     * @param <T>
     * @return
     * @throws ConnectionException
     */
    private <T> T handleResponse(Request request, ResponseCatchData<T> responseCatchData) throws ConnectionException {
        Response response = null;
        try {
            response = httpHelper().execute(request);
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
        if (response.isSuccessful()) {
            try {
                return responseCatchData.catchDate(request, response, callBack());
            } catch (IOException e) {
                throw new ConnectionException("返回结果失败: ", e);
            } catch (MapperException e) {
                throw new MapperException(String.format("转换失败: %s", e.getMessage()), e);
            }
        }
        String responseData = null;
        try {
            responseData = response.body().string();
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
        throw new ConnectionException("Http Code is : " + response.code() + " and response is :" + responseData);
    }

}
