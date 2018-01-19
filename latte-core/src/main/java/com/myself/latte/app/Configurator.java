package com.myself.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2018/1/15.
 * 需要初始化的配置
 */

public class Configurator {
    //存放配置信息的HashMap
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //初始化类实例开始时，将CONFIG_READY状态设为false
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    //线程安全的懒汉模式，获取单例初始化类的实例
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }
    //返回配置的HashMap
    final HashMap<String, Object> getLatteConfigs() {
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
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    //初始化的输入
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }
    //初始化的输入
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
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

    //检查是否初始化完成
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    //输出
    // 获取某一项配置信息
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
