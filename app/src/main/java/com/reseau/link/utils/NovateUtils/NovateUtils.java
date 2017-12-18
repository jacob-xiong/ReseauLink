package com.reseau.link.utils.NovateUtils;

import android.content.Context;

import com.reseau.link.client.ApiHost;
import com.reseau.link.utils.Config;
import com.tamic.novate.Novate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by xiongyan on 2017/12/5.
 */

public class NovateUtils {
    private static Novate mNovate;

    public static Novate getIntance(Context context) {
        if (mNovate == null) {
            mNovate = new Novate.Builder(context)
                    .addHeader(getHeaders()) //添加公共请求头
                    .addParameters(getParameter())//公共参数
                    .connectTimeout(20)  //连接时间 可以忽略
                    .addCookie(false)  //是否同步cooike 默认不同步
                    .addCache(true)  //是否缓存 默认缓存
                    .baseUrl(ApiHost.getBaseUrl()) //base URL
                    .addLog(true) //是否开启log
                    .build();
//                    .cookieManager(new NovateCookieManager()) // 自定义cooike，
//                    .addInterceptor() // 自定义Interceptor
//                    .addNetworkInterceptor() // 自定义NetworkInterceptor
//                    .proxy(proxy) //代理
//                    .client(client)  //clent 默认不需要
//                    .addCache(cache, cacheTime)   //自定义缓存


        }
        return mNovate;
    }

    private static Map<String, Object> getParameter() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Config.OS_KEY, Config.SYSTEM_OS);
        parameters.put(Config.VERSION_NUM_KEY, Config.VERSION_CODE + "0");
        parameters.put(Config.VERSION_KEY, Config.API_VERSION);
        return parameters;
    }

    private static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        return headers;
    }
}
