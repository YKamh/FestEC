package com.myself.festec.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.myself.latte.app.Latte;
import com.myself.festec.example.event.TestEvent;
import com.myself.latte.ec.database.DatabaseManager;
import com.myself.latte.ec.icon.FontEcModule;
import com.myself.latte.net.Interceptors.DebugInterceptor;
import com.myself.latte.net.rx.AddCookieInterceptor;
import com.myself.latte.util.callback.CallbackManager;
import com.myself.latte.util.callback.CallbackType;
import com.myself.latte.util.callback.IGlobalCallback;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/1/15.
 */

public class ExampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://192.168.147.110")
//                .withApiHost("http://www.baidu.com/")
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(1000)
                .withIcon(new FontEcModule())//初始化自定义字体库
                .withInterceptor(new DebugInterceptor("text", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavaScriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                //添加cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .withApiHost("https://www.baidu.com/")
                .configure();
        DatabaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())){
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_POSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())){
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
    }
}
