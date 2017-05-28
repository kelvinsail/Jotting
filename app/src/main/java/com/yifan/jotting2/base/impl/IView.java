package com.yifan.jotting2.base.impl;

/**
 * Created by wuyifan on 2017/5/26.
 */

public interface IView<T> {

    void onSuccess(T object);

    void onError(Throwable error);
}
