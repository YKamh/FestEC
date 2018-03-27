package com.myself.latte.app;

import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.myself.latte.delegates.web.event.Event;
import com.myself.latte.delegates.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Handler;

import org.greenrobot.greendao.annotation.NotNull;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2018/1/15.
 * 需要初始化的配置
 */

public class Configurator {
    //存放配置信息的HashMap
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    //初始化类实例开始时，将CONFIG_READY状态设为false
    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    //线程安全的懒汉模式，获取单例初始化类的实例
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }
    //返回配置的HashMap
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //静态内部类
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }
    //最终初始化的动作
    public final void configure() {
        //初始化字体
        initIcons();
        //初始化初始化状态
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    //初始化的输入
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }
    //初始化的输入
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }
    //初始化字体库
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId){
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret){
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity){
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public final Configurator withJavaScriptInterface(@NotNull String name){
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public final Configurator withWebEvent(@NotNull String name, @NotNull Event event){
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    //检查是否初始化完成
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    //输出
    // 获取某一项配置信息
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null){
            throw new NullPointerException(key.toString() + "IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
