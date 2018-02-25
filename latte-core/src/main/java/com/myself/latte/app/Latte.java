package com.myself.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/15.
 */

public final class Latte {

    public static Configurator init(Context context){
        getConfiguration().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfiguration(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static <T> T getConfiguration(Object key){
        return Configurator.getInstance().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return (Context) getConfiguration().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
