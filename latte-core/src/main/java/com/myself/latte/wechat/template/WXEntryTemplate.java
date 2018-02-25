package com.myself.latte.wechat.template;

import com.myself.latte.activities.ProxyActivity;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.wechat.BaseWXEntryActivity;
import com.myself.latte.wechat.LatteWeChat;

/**
 * Created by Kamh on 2018/2/23.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getIWeChatSignInCallback().onSignInSuccess(userInfo);
    }
}
