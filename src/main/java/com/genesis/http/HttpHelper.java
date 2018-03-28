package com.genesis.http;

import com.genesis.utils.HttpConfig;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Terry
 */
public class HttpHelper {
    public final static MediaType FORM_UTF8 = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    public final static MediaType JSON_UTF8 = MediaType.parse("application/json; charset=utf-8");
    public final static MediaType FORM_GBK = MediaType.parse("application/x-www-form-urlencoded; charset=gbk");
    public final static MediaType JSON_GBK = MediaType.parse("application/json; charset=gbk");
    public final static MediaType XML_UTF8 = MediaType.parse("application/xml; charset=utf-8");

    private final static OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    private final OkHttpClient okHttpClient;

    private final boolean defaultClient;

    public HttpHelper() {
        okHttpClient = OK_HTTP_CLIENT;
        defaultClient = true;
    }

    public HttpHelper(HttpConfig httpConfig) {
        this.okHttpClient = OK_HTTP_CLIENT.newBuilder().connectTimeout(httpConfig.connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(httpConfig.readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(httpConfig.writeTimeout, TimeUnit.MILLISECONDS).build();
        this.defaultClient = false;
    }

    /**
     * 执行请求
     *
     * @param request
     * @return
     * @throws IOException
     */
    public Response execute(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }
}
