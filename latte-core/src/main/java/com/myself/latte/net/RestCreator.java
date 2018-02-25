package com.myself.latte.net;

import com.myself.latte.app.ConfigKeys;
import com.myself.latte.app.Latte;
import com.myself.latte.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/1/19.
 * 网络请求类的必要配置以及参数单例共享构造者
 */

public class RestCreator {

    //线程安全单例的静态内部类,请求参数单例
    private static final class ParamsHolder{
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    /**
     * 这是一种Java并发编程单例模式创建方式，也就是内部类Holder
     * 构造全局Retrofit客户端
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfiguration().get(ConfigKeys.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    /**
     *     同样是一个静态内部类
     *     Service 接口
     */
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    /**
     *     同样是一个静态内部类
     *     Service 接口
     */
    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }

}
