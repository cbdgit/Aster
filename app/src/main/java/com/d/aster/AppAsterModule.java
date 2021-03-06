package com.d.aster;

import android.content.Context;
import android.support.annotation.NonNull;

import com.d.aster.api.API;
import com.d.lib.aster.base.Config;
import com.d.lib.aster.integration.okhttp3.interceptor.HeadersInterceptor;
import com.d.lib.aster.integration.retrofit.RetrofitModule;
import com.d.lib.aster.utils.SSLUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * AppAsterModule
 * Created by D on 2018/12/18.
 **/
public class AppAsterModule extends RetrofitModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull Config.Builder builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put(API.CommonHeader.platform, "Android");
        headers.put(API.CommonHeader.appVersion, "v1.0.0");

        builder.baseUrl(API.API_BASE)
                .headers(headers)
                .headers(new HeadersInterceptor.OnHeadInterceptor() {
                    @Override
                    public void intercept(Map<String, String> heads) {
                        // Add a dynamic request header such as token
                        heads.put("token", "008");
                    }
                })
                .connectTimeout(10 * 1000)
                .readTimeout(10 * 1000)
                .writeTimeout(10 * 1000)
                .retryCount(3)
                .retryDelayMillis(2 * 1000)
                .sslSocketFactory(SSLUtil.getSslSocketFactory(null, null, null))
                .log("RetrofitLog Back = ", Config.Level.BODY)
                .debug(true)
                .build();
    }
}
