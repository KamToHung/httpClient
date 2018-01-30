package com.genesis.httpInterface;

import com.genesis.entity.ResponseBean;
import com.genesis.exception.ConnectionException;
import com.genesis.exception.MapperException;
import com.genesis.http.HttpHelper;
import com.genesis.utils.HttpConfig;
import okhttp3.MediaType;

import java.util.Map;

public interface RequestInterface {


    ObjectMapper objectMapper();

    /**
     * 请求对象
     * @return
     */
    HttpHelper httpHelper();

    /**
     * 回调
     * @return
     */
    ResponseCallBack callBack();

    /**
     * 请求对象
     * @param config
     * @return
     */
    HttpHelper httpHelper(HttpConfig config);

    /**
     *
     * @param url
     * @param headers
     * @param responseType
     * @param value
     * @param <T>
     * @return
     */
    <T> ResponseBean<T> get(String url, Map<String,String> headers, Class<T> responseType, Map<String,String> value) throws ConnectionException;

    /**
     *
     * @param url
     * @param responseType
     * @param value
     * @param <T>
     * @return
     */
    <T> ResponseBean<T> get(String url, Class<T> responseType, Map<String,String> value) throws ConnectionException;

    /**
     *
     * @param url
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     */
    <T> ResponseBean<T> post(String url, Class<T> responseType, String content, MediaType mediaType) throws MapperException, ConnectionException;

    /**
     *
     * @param url
     * @param headers
     * @param responseType
     * @param content
     * @param mediaType
     * @param <T>
     * @return
     */
    <T> ResponseBean<T> post(String url, Map<String,String> headers, Class<T> responseType, String content, MediaType mediaType) throws ConnectionException;
}
