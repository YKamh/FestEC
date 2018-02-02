package com.myself.latte.net.Interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kamh on 2018/2/2.
 */

public abstract class BaseInterceptor implements Interceptor {

    //LinkedHashMap是有序的，HashMap是无序的
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain){
        final HttpUrl url = chain.request().url();
        //获取请求参数的个数
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++){
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    //通过参数Key值获取value
    protected String getUrlParameters(Chain chain, String key){
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    //post请求体获取参数
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++){
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    //post请求体获取参数
    protected String getBodyParameters(Chain chain, String key){
        return getBodyParameters(chain).get(key);
    }
}
