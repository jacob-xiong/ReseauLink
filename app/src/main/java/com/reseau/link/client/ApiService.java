package com.reseau.link.client;

import com.reseau.link.mvp.data.ResultData;
import com.reseau.link.mvp.data.TestData;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author by xiongyan on 2017/11/20.
 */

public interface ApiService {
    @POST("http://10.25.32.231:80/demo/TestServlet")
    Observable<ResultData<TestData>> testLogin(@Query("billType") int billType);
}
