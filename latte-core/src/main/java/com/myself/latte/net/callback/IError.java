package com.myself.latte.net.callback;

/**
 * Created by Administrator on 2018/1/19.
 * Retrofit 请求错误回调接口
 */

public interface IError {

    void onError(int code, String msg);
}
