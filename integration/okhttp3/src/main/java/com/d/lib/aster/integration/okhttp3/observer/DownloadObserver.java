package com.d.lib.aster.integration.okhttp3.observer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.d.lib.aster.callback.ProgressCallback;
import com.d.lib.aster.integration.okhttp3.RequestManager;
import com.d.lib.aster.utils.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.ResponseBody;

/**
 * Observer with Download Callback
 * Created by D on 2017/10/26.
 */
public class DownloadObserver extends AbsObserver<ResponseBody> {
    // The two progress update intervals cannot be less than 1000ms
    private static final int MIN_DELAY_TIME = 1000;

    private final String mPath;
    private final String mName;
    private final Object mTag;
    private final DownloadModel mDownModel = new DownloadModel();
    private final ProgressCallback mCallback;

    public DownloadObserver(final String path, final String name, @Nullable final Object tag,
                            @Nullable ProgressCallback callback) {
        this.mPath = path;
        this.mName = name;
        this.mTag = tag;
        this.mCallback = callback;
    }

    @NonNull
    private File createFile() {
        File dir = new File(mPath);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        return new File(dir.getPath() + File.separator + mName);
    }

    public void cancel() {
        // TODO: @dsiner imp... 2018/12/6
        // dispose();
        if (mCallback == null) {
            return;
        }
        Util.executeMain(new Runnable() {
            @Override
            public void run() {
                mCallback.onCancel();
            }
        });
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        saveFile(responseBody, createFile());
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        onErrorImp(e);
    }

    private void saveFile(@NonNull ResponseBody resp, @NonNull File file) {
        InputStream inputStream = null;
        RandomAccessFile outputStream = null;
        try {
            int readLen;
            int currentLength = 0;
            byte[] buffer = new byte[4096];
            long lastTime = 0;

            inputStream = resp.byteStream();
            outputStream = new RandomAccessFile(file, "rw");
            outputStream.seek(0);

            mDownModel.totalLength = resp.contentLength();

            onProgressImp(mDownModel.currentLength, mDownModel.totalLength);

            while ((readLen = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLen);
                currentLength += readLen;
                mDownModel.currentLength = currentLength;

                long currentTime = System.currentTimeMillis();
                if (currentTime - lastTime >= MIN_DELAY_TIME || lastTime == 0) {
                    lastTime = currentTime;
                    onProgressImp(mDownModel.currentLength, mDownModel.totalLength);
                }
            }
            onSuccessImp();
        } catch (IOException e) {
            e.printStackTrace();
            RequestManager.getIns().remove(mTag);
        } finally {
            okhttp3.internal.Util.closeQuietly(inputStream);
            okhttp3.internal.Util.closeQuietly(outputStream);
            okhttp3.internal.Util.closeQuietly(resp);
        }
    }

    private void onProgressImp(final long currentLength, final long totalLength) {
        if (mCallback == null) {
            return;
        }
        Util.executeMain(new Runnable() {
            @Override
            public void run() {
                mCallback.onProgress(currentLength, totalLength);
            }
        });
    }

    private void onErrorImp(final Throwable e) {
        RequestManager.getIns().remove(mTag);
        if (mCallback == null) {
            return;
        }
        Util.executeMain(new Runnable() {
            @Override
            public void run() {
                mCallback.onError(e);
            }
        });
    }

    private void onSuccessImp() {
        RequestManager.getIns().remove(mTag);
        if (mCallback == null) {
            return;
        }
        Util.executeMain(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess();
            }
        });
    }

    public static class DownloadModel {
        public long currentLength;
        public long totalLength;
    }
}
