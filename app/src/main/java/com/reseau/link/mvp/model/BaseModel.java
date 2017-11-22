package com.reseau.link.mvp.model;

/**
 * @author by xiongyan on 2017/11/20.
 */

public abstract class BaseModel<T extends BaseDataBridge> {
    /**
     * Model完成数据操作后需要回调Presenter，改对象为回调使用的数据桥
     */
    protected T dataBridge;

    /**
     * Presenter在销毁的时候调用该方法，回收相关的引用
     */
    public void release() {
        if (dataBridge != null) {
            dataBridge = null;
        }
    }

}
