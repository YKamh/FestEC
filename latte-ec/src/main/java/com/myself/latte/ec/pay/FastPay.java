package com.myself.latte.ec.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myself.latte.app.ConfigKeys;
import com.myself.latte.app.Latte;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.ui.loader.LatteLoader;
import com.myself.latte.wechat.LatteWeChat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created by Kamh on 2018/4/19.
 */

public class FastPay implements View.OnClickListener{

    //设置支付回调监听
    private IAliPayResultListener mListener = null;
    private Activity mActivity;

    private Dialog mDialog = null;
    private int mOrderId = -1;

    private FastPay(LatteDelegate delegate){
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteDelegate delegate){
        return new FastPay(delegate);
    }

    public void beginPayDialog(){
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null){
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(lp);

            window.findViewById(R.id.btn_dialog_ali_pay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    public FastPay setPayResultListener(IAliPayResultListener listener){
        this.mListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId){
        this.mOrderId = orderId;
        return this;
    }

    private final void aliPay(int orderId){
        final String singUrl = "";
        //获取签名字符串
        RestClient.builder()
                .url(singUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSONObject.parseObject(response).getString("result");
                        //必须调用异步客户端支付接口
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);

                    }
                })
                .build()
                .post();
    }

    private final void weChatPay(int orderId){
        LatteLoader.stopLoading();
        final String weChatPrePayUrl = "";
        final IWXAPI iwxapi = LatteWeChat.getInstance().getWXAPI();
        final String appId = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result = JSON.parseObject(response).getJSONObject("result");
                        final String prePayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerId");
                        final String packageValue = result.getString("packageValue");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("nonceStr");
                        final String paySign = result.getString("paySign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.appId = appId;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_ali_pay){
            aliPay(mOrderId);
            mDialog.cancel();
        }else if (id == R.id.btn_dialog_wechat){
            weChatPay(mOrderId);
            mDialog.cancel();
        }else if (id == R.id.btn_dialog_pay_cancel){
            mDialog.cancel();
        }
    }
}
