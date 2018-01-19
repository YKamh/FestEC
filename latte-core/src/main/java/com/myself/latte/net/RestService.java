package com.myself.latte.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/1/18.
 * 集中处理Retrofit的Call回调接口
 */

public interface RestService {

    //QeryMap就是我们普通的Get请求以键值对的方式拼接到url里面
    @GET
    Call<String> get(@Url String string, @QueryMap Map<String, Object> params);

    //@FiledMap就是请求体
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @DELETE
    Call<String> delete(@Url String string, @QueryMap Map<String, Object> params);

    //Streaming注解是为了防止由于下载文件过大造成内存溢出，因为下载过程是先把文件读到内存中，再由内存写到文件中，文件过大时就容易造成内存溢出。
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String string, @QueryMap Map<String, Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part params);
}
