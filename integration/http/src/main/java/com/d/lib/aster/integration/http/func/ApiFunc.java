package com.d.lib.aster.integration.http.func;

import com.d.lib.aster.integration.http.client.ResponseBody;
import com.d.lib.aster.scheduler.callback.Function;
import com.d.lib.aster.utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * ResponseBody to T
 */
public class ApiFunc<T> implements Function<ResponseBody, T> {
    private Type mType;

    public ApiFunc(Type type) {
        this.mType = type;
    }

    @Override
    public T apply(ResponseBody responseBody) throws Exception {
        Util.printThread("Aster_thread gsonFormat");
        Gson gson = new Gson();
        String json;
        try {
            json = responseBody.string();
            responseBody.close();
            if (mType.equals(String.class)) {
                return (T) json;
            } else {
                return gson.fromJson(json, mType);
            }
        } catch (IOException e) {
            if (responseBody != null) {
                responseBody.close();
            }
            throw new JsonParseException("JSON PARSE ERROR!");
        }
    }
}
