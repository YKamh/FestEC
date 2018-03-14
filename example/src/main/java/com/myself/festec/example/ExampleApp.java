package com.myself.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.myself.latte.app.Latte;
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
                .withApiHost("http://192.168.147.115/")
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(1000)
                .withIcon(new FontEcModule())//初始化自定义字体库
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
