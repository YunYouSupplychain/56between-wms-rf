package com.yunyou.androidwmsrf.app;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.app.utils.GsonUtils;
import com.yunyou.androidwmsrf.mvp.model.entity.BaseResponse;
import com.yunyou.androidwmsrf.mvp.ui.activity.LoginActivity;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.utils.ArmsUtils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ================================================
 * 展示 {@link GlobalHttpHandler} 的用法
 */
public class GlobalHttpHandlerImpl implements GlobalHttpHandler {
    private final Context context;

    public GlobalHttpHandlerImpl(Context context) {
        this.context = context;
    }

    @Override
    public Response onHttpResultResponse(String httpResponse, Interceptor.Chain chain, Response response) {
        BaseResponse baseResponse = GsonUtils.fromJson(httpResponse, new TypeToken<BaseResponse>() {
        }.getType());
        if (baseResponse.getErrorCode() == 0 && baseResponse.getMsg().contains("登录")) {
            Thread t = new Thread() {
                public void run() {
                    Looper.prepare();
                    Toast.makeText(context, baseResponse.getMsg(), Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            };
            t.start();
            try {
                AppLifecyclesImpl.playSound();
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {

            } finally {
                t.interrupt();
                ArmsUtils.startActivity(new Intent(context, LoginActivity.class));
            }
        }
        // ArmsUtils.startActivity(new Intent(context, MainActivity.class));
        // 这里可以先客户端一步拿到每一次http请求的结果,可以解析成json,做一些操作,如检测到token过期后重新请求token,并重新执行请求
        /* 这里如果发现token过期,可以先请求最新的token,然后在拿新的token放入request里去重新请求
            注意在这个回调之前已经调用过proceed,所以这里必须自己去建立网络请求,如使用okhttp使用新的request去请求
            create a new request and modify it accordingly using the new token
            Request newRequest = chain.request().newBuilder().header("token", newToken).build();
            retry the request
            response.body().close();
            如果使用okhttp将新的请求,请求成功后,将返回的response  return出去即可
            如果不需要返回新的结果,则直接把response参数返回出去 */

        return response;
    }

    // 这里可以在请求服务器之前可以拿到request,做一些操作比如给request统一添加token或者header以及参数加密等操作
    @Override
    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
        // 如果需要再请求服务器之前做一些操作,则重新返回一个做过操作的的request如增加header,不做操作则直接返回request参数
        // return chain.request().newBuilder().header("token", tokenId).build();
        String url = request.url().url().toString();
        String sessionId = SPUtils.getInstance().getString(Constants.USER_SESSION_ID);

        url += url.contains("?") ? "&mobileLogin=true" : "?mobileLogin=true";
        return chain.request().newBuilder()
                .url(url)
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Cookie", "JSESSIONID=" + sessionId + ";yunyou.session.id=" + sessionId)
                .build();
    }

}