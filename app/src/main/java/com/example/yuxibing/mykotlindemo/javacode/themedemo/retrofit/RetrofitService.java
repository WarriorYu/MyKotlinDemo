package com.example.yuxibing.mykotlindemo.javacode.themedemo.retrofit;


import java.net.URI;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public interface RetrofitService {
    /**
     * 使用 POST 使用@Field方式时需要注意两点：
     * <p>
     * 必须加上 @FormUrlEncoded标签，否则会抛异常。
     * 必须要有参数，否则会抛异常, 源码抛异常的地方如下：
     */
    String BASE_URL = "https://www.baidu.com";

    @Streaming
    @GET
    Call<ResponseBody> getSysTheme(@Url String url);
}
