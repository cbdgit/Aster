package com.d.lib.rxnet.request;

import com.d.lib.rxnet.api.RetrofitAPI;
import com.d.lib.rxnet.base.RetrofitClient;

import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;

/**
 * Singleton
 * Created by D on 2017/10/24.
 */
public class PutRequest extends HttpRequest<PutRequest> {

    public PutRequest(String url) {
        super(url);
    }

    public PutRequest(String url, Map<String, String> params) {
        super(url, params);
    }

    @Override
    protected void prepare() {
        observable = RetrofitClient.getRetrofit(config).create(RetrofitAPI.class).put(url, params);
    }

    @Override
    public PutRequest baseUrl(String baseUrl) {
        return super.baseUrl(baseUrl);
    }

    @Override
    public PutRequest headers(Map<String, String> headers) {
        return super.headers(headers);
    }

    @Override
    public PutRequest connectTimeout(long timeout) {
        return super.connectTimeout(timeout);
    }

    @Override
    public PutRequest readTimeout(long timeout) {
        return super.readTimeout(timeout);
    }

    @Override
    public PutRequest writeTimeout(long timeout) {
        return super.writeTimeout(timeout);
    }

    @Override
    public PutRequest sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        return super.sslSocketFactory(sslSocketFactory);
    }

    @Override
    public PutRequest addInterceptor(Interceptor interceptor) {
        return super.addInterceptor(interceptor);
    }

    @Override
    public PutRequest addNetworkInterceptors(Interceptor interceptor) {
        return super.addNetworkInterceptors(interceptor);
    }

    @Override
    public PutRequest retryCount(int retryCount) {
        return super.retryCount(retryCount);
    }

    @Override
    public PutRequest retryDelayMillis(long retryDelayMillis) {
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
            observable = RetrofitClient.getIns().create(RetrofitAPI.class).put(url, params);
        }
    }
}
