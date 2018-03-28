package com.genesis.utils;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Terry
 */
public class HandleUrlUtils {

    private final static Joiner.MapJoiner MAP_JOINER = Joiner.on("&").withKeyValueSeparator("=");

    public static String splitUrl(String url, Map<String,?> map) {

        if (map != null && !map.isEmpty()) {
            url += "?";
            url += MAP_JOINER.join(map);
        }
        return url;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>(2);
        map.put("name","Terry");
        map.put("sex","male");
        System.out.println(splitUrl("www.baidu.com",map));
    }
}
