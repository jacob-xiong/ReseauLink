package com.reseau.link.mvp.model;

import com.reseau.link.mvp.data.ResultData;
import com.reseau.link.mvp.data.TestData;

/**
 * @author by xiongyan on 2017/11/20.
 *         Presenter调用Model层过后，用于Model回调Presenter，数据桥
 */

public interface BaseDataBridge<T extends ResultData> {
    /**
     * 通用成功回调方法
     *
     * @param data 成功后返回的数据
     */
    void onSuccess(T data);

    /**
     * 通用的失败回调方法
     */
    void onFailure(Throwable throwable);

    /**
     * 测试回调
     */
    interface TestDataBridge extends BaseDataBridge<ResultData<TestData>> {
        void loginSuccess(ResultData<TestData> resultData);


        void loginFaild(String msg);

    }
}
