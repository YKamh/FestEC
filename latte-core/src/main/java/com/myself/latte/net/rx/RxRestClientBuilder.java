package com.myself.latte.net.rx;

import android.content.Context;

import com.myself.latte.net.RestClient;
import com.myself.latte.net.RestCreator;
import com.myself.latte.net.callback.IError;
import com.myself.latte.net.callback.IFailure;
import com.myself.latte.net.callback.IRequest;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/1/19.
 * Builder无非就是传值的操作，也就是最累的那个建造者
 * 为创建一个产品对象的各个部件指定抽象接口。
 */

public class RxRestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;

    //只允许同包的RestClient去new他
    RxRestClientBuilder(){

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key, value);
        return this;
    }


    public final RxRestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset-UFT-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    //建造完成后就做最后的交付，还给Client
    public final RxRestClient build(){
        return new RxRestClient(mUrl, PARAMS, mBody, mContext, mLoaderStyle, mFile);
    }
}
