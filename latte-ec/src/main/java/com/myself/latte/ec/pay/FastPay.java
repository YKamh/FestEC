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

import com.alibaba.fastjson.JSONObject;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;

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

    public final void aliPay(int orderId){
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_ali_pay){
            aliPay(mOrderId);
            mDialog.cancel();
        }else if (id == R.id.btn_dialog_wechat){
            mDialog.cancel();
        }else if (id == R.id.btn_dialog_pay_cancel){
            mDialog.cancel();
        }
    }
}
