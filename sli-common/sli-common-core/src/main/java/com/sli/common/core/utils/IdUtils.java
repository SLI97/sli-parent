package com.sli.common.core.utils;

import java.util.UUID;

/**
 * 封装ID算法的工具类.
 */
public class IdUtils {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String simpleUUID() {
        return randomUUID().replaceAll("-", "");
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成.
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

}
