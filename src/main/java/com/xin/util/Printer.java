package com.xin.util;

import com.alibaba.fastjson.JSON;

/**
 * @author Three
 * @since 18/6/26下午3:08
 */
public class Printer {
    public static void jsonPrint(Object o) {
        System.out.println(JSON.toJSONString(o));
    }
}
