package com.reseau.link.mvp.model;

import com.reseau.link.client.NetWorkClient;
import com.reseau.link.mvp.data.ResultData;
import com.reseau.link.mvp.data.TestData;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class TestModel extends BaseModel<BaseDataBridge.TestDataBridge> {
    public TestModel(BaseDataBridge.TestDataBridge dataBridge) {
        this.dataBridge = dataBridge;
    }

    public Subscription testLogin(int loginType) {
        return NetWorkClient.getApiService().testLogin(loginType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResultData<TestData>>() {
                    @Override
                    public void call(ResultData<TestData> testData) {
                        if (testData.getStatusCode().equals("0")) {
                            dataBridge.loginSuccess(testData);
                        } else {
                            dataBridge.loginFaild(testData.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dataBridge.onFailure(throwable);
                    }
                });
    }

}
