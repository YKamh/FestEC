package com.myself.latte.net;

import android.content.Context;

import com.myself.latte.app.Latte;
import com.myself.latte.net.callback.IError;
import com.myself.latte.net.callback.IFailure;
import com.myself.latte.net.callback.IRequest;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.net.callback.RequestCallbacks;
import com.myself.latte.ui.LatteLoader;
import com.myself.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2018/1/18.
 * 网络请求封装
 */

public class RestClient {

    //用final声明的变量如果没有赋值，必须在构造方法里面给他们赋值
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest iRequest,
                      ISuccess iSuccess,
                      IFailure iFailure,
                      IError iError,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.IREQUEST = iRequest;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    //创建构造者,让建造者去建造
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (IREQUEST != null){
            IREQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method){
            case GET:
                call= service.get(URL, PARAMS);
                break;
            case POST:
                call= service.post(URL, PARAMS);
                break;
            case PUT:
                call= service.put(URL, PARAMS);
                break;
            case DELETE:
                call= service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call!= null){
            //enqueue是一个异步方法
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                IREQUEST,
                ISUCCESS,
                IFAILURE,
                IERROR,
                LOADER_STYLE
        );
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
