package com.myself.latte.wechat.template;

import android.widget.Toast;

import com.myself.latte.activities.ProxyActivity;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.wechat.BaseWXActivity;
import com.myself.latte.wechat.BaseWXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;

/**
 * Created by Kamh on 2018/2/23.
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPauCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
