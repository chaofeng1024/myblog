package org.blog.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static Map<String,Object> map = new HashMap<>();

    public static Map getSuccessMap(Object data){
        map.put("data",data);
        map.put("msg","success");
        map.put("code",1);
        return map;
    }

    public static Map getErrorMap(String msg,Integer errorNum){
        map.put("msg",msg);
        map.put("code",errorNum);
        return map;
    }
}
