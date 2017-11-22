package com.reseau.link.mvp.presenter;

import android.content.Context;

import com.reseau.link.mvp.model.BaseModel;
import com.reseau.link.mvp.view.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author by xiongyan on 2017/11/20.
 * 创建Presenter来连接M层和V层
 */

public abstract class BasePresenter<T extends BaseView, K extends BaseModel> {
    /**
     * RxJava中的异步操作，防止对象销毁的时候还有异步操作正在进行中，导致内存泄露的风险
     */
    protected Subscription subscription;

    /**
     * 用来记录多个subscription
     */
    protected CompositeSubscription compositeSubscription;

    /**
     * Presenter持有的View
     */
    protected T view;

    /**
     * Presenter持有的Model
     */
    protected K model;

    /**
     * Presenter持有的Context对象
     */
    protected Context context;

    /**
     * presenter构造方法，需要有View和上下文
     * @param view
     * @param context
     */
    public BasePresenter(T view, Context context) {
        this.view = view;
        this.context = context;
    }

    /**
     * 改方法在mvp框架的View层自动调用（onDestroy中调用），用于当前View销毁时，自动回收对象中的一些引用
     */
    public void release() {
        if (this.subscription != null && !this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        if (this.compositeSubscription != null && !this.compositeSubscription.isUnsubscribed()) {
            this.compositeSubscription.clear();
        }
        if (model != null) {
            model.release();
        }
        subscription = null;
        compositeSubscription = null;
        view = null;
        model = null;
        context = null;
    }

    /**
     * 用于当前多个异步线程时，如果要取代当前进行的线程，可以将新的Subscription传入
     *
     * @param subscription
     */
    public final void repSubscription(Subscription subscription) {
        if (this.subscription != null && !this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        this.subscription = subscription;
    }

    /**
     * 多个异步线程时，要添加一个异步线程，可以用该方法传入新的Subscription
     *
     * @param subscription
     */
    public final void addSubscription(Subscription subscription) {
        compositeSubscription = (compositeSubscription == null) ? new CompositeSubscription() : compositeSubscription;
        compositeSubscription.add(subscription);
    }



}
