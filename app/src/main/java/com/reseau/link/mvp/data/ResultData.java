package com.reseau.link.mvp.data;

import java.io.Serializable;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class ResultData<T> implements Serializable {
    /**
     * 返回状态码
     */
    private String statusCode;

    /**
     * 返回的message信息
     */
    private String message;

    /**
     * 返回数据data对象
     */
    T data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
