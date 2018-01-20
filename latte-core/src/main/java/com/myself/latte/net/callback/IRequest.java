package com.myself.latte.net.callback;

/**
 * Created by Administrator on 2018/1/19.
 * Retrofit 请求开始回调接口
 */

public interface IRequest {

    void onRequestStart();

    void onRequestEnd();
}
