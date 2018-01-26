package com.myself.latte.net.callback;

import com.myself.latte.ui.LatteLoader;
import com.myself.latte.ui.LoaderStyle;

import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/1/20.
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final LoaderStyle LOADER_STYLE;
    //Handler 尽量声明成static类型，这样可以避免一些内存泄漏
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (ISUCCESS !=null){
                    ISUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if (IERROR !=null){
                IERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null){
            IFAILURE.onFailure();
        }

        if (IREQUEST !=null){
            IREQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
