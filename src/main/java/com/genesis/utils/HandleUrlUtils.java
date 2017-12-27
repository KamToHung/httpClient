package com.genesis.utils;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;

public class HandleUrlUtils {

    private final static Joiner.MapJoiner MAP_JOINER = Joiner.on("&").withKeyValueSeparator("=");

    public static String splitUrl(String url, Map<String,String> map) {

        if (map != null && !map.isEmpty()) {
            url += "?";
            url += MAP_JOINER.join(map);
        }
        return url;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("name","Terry");
        map.put("sex","male");
        System.out.println(splitUrl("www.baidu.com",map));
    }
}
