package com.d.lib.aster.integration.http.interceptor;

import android.support.annotation.NonNull;

import com.d.lib.aster.interceptor.IHeadersInterceptor;
import com.d.lib.aster.interceptor.IInterceptor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Request header interception
 */
public class HeadersInterceptor extends IHeadersInterceptor
        implements IInterceptor<HttpURLConnection, HttpURLConnection> {

    public HeadersInterceptor(Map<String, String> headers) {
        super(headers);
    }

    @Override
    public HttpURLConnection intercept(@NonNull HttpURLConnection chain) throws IOException {
        if (mHeaders != null && mHeaders.size() > 0) {
            Set<String> keys = mHeaders.keySet();
            for (String headerKey : keys) {
                chain.setRequestProperty(headerKey, mHeaders.get(headerKey));
            }
        }

        if (mOnHeadInterceptor != null) {
            Map<String, String> headers = new LinkedHashMap<>();
            mOnHeadInterceptor.intercept(headers);
            if (headers.size() > 0) {
                Set<String> keys = headers.keySet();
                for (String headerKey : keys) {
                    chain.setRequestProperty(headerKey, headers.get(headerKey));
                }
            }
        }

        return chain;
    }
}
