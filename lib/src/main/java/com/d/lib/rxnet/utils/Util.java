package com.d.lib.rxnet.utils;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Util
 * Created by D on 2017/10/25.
 */
public class Util {

    /**
     * Print the thread information of the current code
     */
    public static void printThread(String tag) {
        ULog.d(tag + " " + Thread.currentThread().getId() + "--NAME--" + Thread.currentThread().getName());
    }

    /**
     * Get the first generic type, interface only
     */
    public static <T> Class<T> getFirstCls(T t) {
        Type[] types = t.getClass().getGenericInterfaces();
        Type[] params = ((ParameterizedType) types[0]).getActualTypeArguments();
        Class<T> reponseClass = (Class) params[0];
        return reponseClass;
    }

    /**
     * Delete file/folder
     */
    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                return;
            }
            for (File f : files) {
                deleteFile(f);
            }
            // If you want to keep the folder, just delete the file, please comment this line
            file.delete();
        } else if (file.exists()) {
            file.delete();
        }
    }
}