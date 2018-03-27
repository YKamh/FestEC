package com.myself.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.myself.latte.app.Latte;
import com.myself.festec.example.event.TestEvent;
import com.myself.latte.ec.database.DatabaseManager;
import com.myself.latte.ec.icon.FontEcModule;
import com.myself.latte.net.Interceptors.DebugInterceptor;

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
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
