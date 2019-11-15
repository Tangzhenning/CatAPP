package com.demo.lucar.utils;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private OkHttpClient client;
    private static HttpUtils mClient;
    private Context context;

    private HttpUtils(Context c) {
        context = c;
        client = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .build();
    }

    public static HttpUtils getInstance(Context c) {
        if (mClient == null) {
            mClient = new HttpUtils(c);
        }
        return mClient;
    }


    public void get(String url, HashMap<String, String> param, MyCallback callback) {
        if (!param.isEmpty()) {
            StringBuffer buffer = new StringBuffer(url);
            buffer.append('?');
            for (Map.Entry<String, String> entry : param.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append('=');
                buffer.append(entry.getValue());
                buffer.append('&');
            }
            buffer.deleteCharAt(buffer.length() - 1);
            url = buffer.toString();
        }
        ToastUtils.showLog("get：" + url);
        Request.Builder builder = new Request.Builder().url(url);
        builder.method("GET", null);
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    callback.success(response.body().string());
                } else {
                    callback.failed(new IOException());
                }

            }
        });
    }

    public void get(String url, MyCallback callback) {
        get(url, new HashMap<String, String>(), callback);
    }

    public void post(String url, HashMap<String, String> param, MyCallback callback) {
        FormBody.Builder formBody = new FormBody.Builder();
        if (!param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        ToastUtils.showLog("post：" + url);
        ToastUtils.showLog("postBody：" + formBody.toString());
        RequestBody form = formBody.build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(form)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    callback.success(response.body().string());
                } else {
                    callback.failed(new IOException());
                }
            }
        });
    }

    public interface MyCallback {
        void success(String res) throws IOException;

        void failed(IOException e);
    }
}
