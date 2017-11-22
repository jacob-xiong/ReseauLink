package com.reseau.link.mvp.view;

import com.reseau.link.mvp.data.TestData;

/**
 * @author by xiongyan on 2017/11/20.
 */

public interface TestView extends BaseView {
    /**
     * 登录成功
     */
    void loginSuccess(TestData data);

    /**
     * 登录失败
     *
     * @param msg
     */
    void loginFail(String msg);

}
