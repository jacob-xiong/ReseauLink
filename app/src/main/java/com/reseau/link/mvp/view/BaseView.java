package com.reseau.link.mvp.view;

import com.reseau.link.mvp.data.ResultData;

/**
 * @author by xiongyan on 2017/11/20.
 */

public interface BaseView {
    /**
     * 加载失败时调用方法
     */
    void loadFailure(Throwable throwable);

    /**
     * 当返回结果异常的时候调用，
     *
     * @param result
     */
    void onException(ResultData result);
}
