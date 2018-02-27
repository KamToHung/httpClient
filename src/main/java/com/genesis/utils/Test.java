package com.genesis.utils;

import com.genesis.entity.ResponseBean;
import com.genesis.exception.ConnectionException;
import com.genesis.http.HttpHelper;
import com.genesis.httpInterface.ObjectMapper;
import com.genesis.httpInterface.RequestInterface;
import com.genesis.templet.json.JsonHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: Terry
 * @create 2018-02-05 18:16
 */
public class Test {

    public static void main(String[] args) throws ConnectionException {
        RequestInterface requestInterface = new JsonHelper(new HttpConfig.Builder().connectTimeout(60000)
                .keepAlive(5).maxConnection(5).readTimeout(10000).writeTimeout(10000).build());
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Terry");
        map.put("sex", "male");
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "add cookies here");
        ResponseBean<User> response = requestInterface.getEntity("url", header, User.class, map);
        User user = response.getValue();
        System.out.println(ObjectMapper.DEFAULT_MAPPER_JSON.toString(user));
    }
}
