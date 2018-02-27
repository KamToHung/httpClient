## httpclient
httpclient是一个处理http的客户端,它基于Okhttp3实现的.模板参照Spring的Resttemplate.主要用来解决公司之前的项目的Http接口不统一的问题.之前公司使用的是apache的httpClient,性能方面Okhttp比httpClient好很多.Okhttp3有着良好的封装,容易配置,并且可以配置Http连接池等业务逻辑添加很方便.
## 版本
1.0.0 初始版本，实现了httpClient基本功能
1.0.1 添加了可以直接返回对象的方法，而不需要在ResponseBean中取返回值
## 开发
- 自定义接口参数
```bash
RequestInterface requestInterface = new JsonHelper(
                new HttpConfig.Builder().connectTimeout(60000)
                                        .keepAlive(5)
                                        .maxConnection(5)
                                        .readTimeout(10000)
                                        .writeTimeout(10000)
                                        .build());
```

- post请求
```bash
 RequestInterface requestInterface = new JsonHelper(new HttpConfig.Builder().connectTimeout(60000)
                .keepAlive(5).maxConnection(5).readTimeout(10000).writeTimeout(10000).build());
        Person person = new Person();
        person.setName("Terry");
        person.setSex("male");
        ResponseBean<User> response = requestInterface.post("url", User.class, ObjectMapper.DEFAULT_MAPPER_JSON.toString(person), HttpHelper.JSON_UTF8);
        User user = response.getValue();
        System.out.println(ObjectMapper.DEFAULT_MAPPER_JSON.toString(user));
```
- get请求
```bash
  RequestInterface requestInterface = new JsonHelper(new HttpConfig.Builder().connectTimeout(60000)
                .keepAlive(5).maxConnection(5).readTimeout(10000).writeTimeout(10000).build());
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Terry");
        map.put("sex", "male");
        ResponseBean<User> response = requestInterface.get("url", User.class, map);
        User user = response.getValue();
        System.out.println(ObjectMapper.DEFAULT_MAPPER_JSON.toString(user));
```
- post添加header
```bash
   RequestInterface requestInterface = new JsonHelper(new HttpConfig.Builder().connectTimeout(60000)
                .keepAlive(5).maxConnection(5).readTimeout(10000).writeTimeout(10000).build());
        Person person = new Person();
        person.setName("Terry");
        person.setSex("male");
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "add cookies here");
        ResponseBean<User> response = requestInterface.post("url", header, User.class, ObjectMapper.DEFAULT_MAPPER_JSON.toString(person), HttpHelper.JSON_UTF8);
        User user = response.getValue();
        System.out.println(ObjectMapper.DEFAULT_MAPPER_JSON.toString(user));
```
- get添加header
```bash
 RequestInterface requestInterface = new JsonHelper(new HttpConfig.Builder().connectTimeout(60000)
                .keepAlive(5).maxConnection(5).readTimeout(10000).writeTimeout(10000).build());
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Terry");
        map.put("sex", "male");
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "add cookies here");
        ResponseBean<User> response = requestInterface.get("url", header, User.class, map);
        User user = response.getValue();
        System.out.println(ObjectMapper.DEFAULT_MAPPER_JSON.toString(user));
```

## 一些问题
   本httpclient采用了jackson作为json和xml序列化,建议在使用idea的GsonFormat(此插件会添加jackson注解)插件来生成vo,如果没有添加注解的话要根据jackson对json/xml所支持的默认规则来定义参数.