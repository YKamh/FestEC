package com.myself.latte.ec.pay;

/**
 * Created by Kamh on 2018/4/19.
 */

public interface IAliPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFailed();

    void onPayCancel();

    void onPayConnectError();
}
