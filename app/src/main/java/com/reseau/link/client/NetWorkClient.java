package com.reseau.link.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reseau.link.utils.Config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class NetWorkClient {
    public static final String API_VERSION = "v1.0.0";
    public static ApiService getApiService() {
        return new Retrofit.Builder()
                .baseUrl(ApiHost.getUrlTest())
                .addConverterFactory(GsonConverterFactory.create(getDefaultGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getDefaultOkHttpClient())
                .build().create(ApiService.class);
    }

    public static ApiService getApiService(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(getDefaultGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getDefaultOkHttpClient())
                .build().create(ApiService.class);
    }



    private static Gson getDefaultGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
    }

    private static OkHttpClient getDefaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
//                                .addHeader("token", User.getLoginToken())
//                                .addHeader("station", User.getStationCode())
                                .addHeader("os", Config.SYSTEM_OS)
                                .addHeader("versionNum",Config.VERSION_CODE + "0")
                                .addHeader("version", API_VERSION);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();
    }
}
