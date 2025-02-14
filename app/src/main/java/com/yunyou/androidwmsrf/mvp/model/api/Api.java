package com.yunyou.androidwmsrf.mvp.model.api;

/**
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class Api {
    public static String APP_DOMAIN = "http://192.168.0.1:8080/56between/a/";
    // 最大重试次数
    public static int MAX_RETRIES = 0;
    // 重试间隔时间
    public static int RETRY_DELAY_SECOND = 0;
}