package com.reseau.link.mvp.presenter;

import android.content.Context;

import com.reseau.link.mvp.data.ResultData;
import com.reseau.link.mvp.data.TestData;
import com.reseau.link.mvp.model.BaseDataBridge;
import com.reseau.link.mvp.model.TestModel;
import com.reseau.link.mvp.view.TestView;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class TestPresenter extends BasePresenter<TestView, TestModel> implements
        BaseDataBridge.TestDataBridge {
    private TestData testData;

    /**
     * presenter构造方法，需要有View和上下文
     *
     * @param view
     * @param context
     */
    public TestPresenter(TestView view, Context context) {
        super(view, context);
        model = new TestModel(this);
    }

    @Override
    public void onSuccess(ResultData<TestData> data) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void loginSuccess(ResultData<TestData> resultData) {
        testData = resultData.getData();
        view.loginSuccess(testData);
    }

    @Override
    public void loginFaild(String msg) {
        view.loginFail(msg);
    }

    public void testLogin(int billType) {
        addSubscription(model.testLogin(billType));
    }
}
