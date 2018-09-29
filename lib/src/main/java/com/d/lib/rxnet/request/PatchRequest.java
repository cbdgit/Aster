package com.d.lib.rxnet.request;

import com.d.lib.rxnet.api.RetrofitAPI;
import com.d.lib.rxnet.base.RetrofitClient;

import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;

/**
 * Created by D on 2017/10/24.
 */
public class PatchRequest extends HttpRequest<PatchRequest> {

    public PatchRequest(String url) {
        super(url);
    }

    public PatchRequest(String url, Map<String, String> params) {
        super(url, params);
    }

    @Override
    protected void prepare() {
        observable = RetrofitClient.getRetrofit(config).create(RetrofitAPI.class).patch(url, params);
    }

    @Override
    public PatchRequest baseUrl(String baseUrl) {
        return super.baseUrl(baseUrl);
    }

    @Override
    public PatchRequest headers(Map<String, String> headers) {
        return super.headers(headers);
    }

    @Override
    public PatchRequest connectTimeout(long timeout) {
        return super.connectTimeout(timeout);
    }

    @Override
    public PatchRequest readTimeout(long timeout) {
        return super.readTimeout(timeout);
    }

    @Override
    public PatchRequest writeTimeout(long timeout) {
        return super.writeTimeout(timeout);
    }

    @Override
    public PatchRequest sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        return super.sslSocketFactory(sslSocketFactory);
    }

    @Override
    public PatchRequest addInterceptor(Interceptor interceptor) {
        return super.addInterceptor(interceptor);
    }

    @Override
    public PatchRequest addNetworkInterceptors(Interceptor interceptor) {
        return super.addNetworkInterceptors(interceptor);
    }

    @Override
    public PatchRequest retryCount(int retryCount) {
        return super.retryCount(retryCount);
    }

    @Override
    public PatchRequest retryDelayMillis(long retryDelayMillis) {
        return super.retryDelayMillis(retryDelayMillis);
    }

    /**
     * Singleton
     */
    public static class Singleton extends HttpRequest.Singleton<Singleton> {

        public Singleton(String url) {
            super(url);
        }

        public Singleton(String url, Map<String, String> params) {
            super(url, params);
        }

        @Override
        protected void prepare() {
            observable = RetrofitClient.getIns().create(RetrofitAPI.class).patch(url, params);
        }
    }
}
