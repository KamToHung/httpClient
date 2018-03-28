package com.genesis;

import com.genesis.entity.ResponseBean;
import com.genesis.exception.ConnectionException;
import com.genesis.httpinterface.ObjectMapper;
import com.genesis.httpinterface.RequestInterface;
import com.genesis.templet.json.JsonHelper;
import com.genesis.utils.HttpConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: Terry
 * @create 2018-03-28 10:36
 */
public class Test {
    @org.junit.Test
    public void test01() throws ConnectionException {
        RequestInterface requestInterface = new JsonHelper(new HttpConfig.Builder().connectTimeout(60000)
                .keepAlive(5).maxConnection(5).readTimeout(10000).writeTimeout(10000).build());
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", "Terry");
        map.put("sex", "male");
        Map<String, String> header = new HashMap<>(3);
        header.put("Cookie", "add cookies here");
        ResponseBean<User> response = requestInterface.getEntity("url", header, User.class, map);
        User user = response.getValue();
        System.out.println(ObjectMapper.DEFAULT_MAPPER_JSON.toString(user));
    }
}
