package com.d.lib.aster.integration.http.request;

import com.d.lib.aster.base.Params;
import com.d.lib.aster.callback.AsyncCallback;
import com.d.lib.aster.callback.SimpleCallback;
import com.d.lib.aster.interceptor.IHeadersInterceptor;
import com.d.lib.aster.interceptor.IInterceptor;
import com.d.lib.aster.scheduler.Observable;

import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

/**
 * Singleton
 * Created by D on 2017/10/24.
 */
public class PutRequest extends HttpRequest<PutRequest> {

    public PutRequest(String url, Params params) {
        super(url, params);
    }

    @Override
    protected void prepare() {
        mObservable = getClient().create().put(mUrl, mParams);
    }

    @Override
    public <T> void request(SimpleCallback<T> callback) {
        super.request(callback);
    }

    @Override
    public <T, R> void request(AsyncCallback<T, R> callback) {
        super.request(callback);
    }

    @Override
    public <T> Observable.Observe<T> observable(Class<T> clazz) {
        return super.observable(clazz);
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
    public PutRequest headers(IHeadersInterceptor.OnHeadInterceptor onHeadInterceptor) {
        return super.headers(onHeadInterceptor);
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
    public PutRequest addInterceptor(IInterceptor interceptor) {
        return super.addInterceptor(interceptor);
    }

    @Override
    public PutRequest addNetworkInterceptors(IInterceptor interceptor) {
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

        public Singleton(String url, Params params) {
            super(url, params);
        }

        @Override
        protected void prepare() {
            mObservable = getClient().create().put(mUrl, mParams);
        }

        @Override
        public <T> void request(SimpleCallback<T> callback) {
            super.request(callback);
        }

        @Override
        public <T, R> void request(AsyncCallback<T, R> callback) {
            super.request(callback);
        }

        @Override
        public <T> Observable.Observe<T> observable(Class<T> clazz) {
            return super.observable(clazz);
        }
    }
}
