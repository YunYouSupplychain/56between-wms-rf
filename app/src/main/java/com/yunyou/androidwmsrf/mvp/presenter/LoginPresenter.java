package com.yunyou.androidwmsrf.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.mvp.contract.LoginContract;
import com.yunyou.androidwmsrf.mvp.model.api.Api;
import com.yunyou.androidwmsrf.mvp.model.webservice.GetAppVersionResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.LoginRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.LoginResponse;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yunyou.androidwmsrf.R;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getAppVersion(String appId) {
        mModel.getAppVersion(appId)
                .subscribeOn(Schedulers.io())
                // 遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .retryWhen(new RetryWithDelay(Api.MAX_RETRIES, Api.RETRY_DELAY_SECOND))
                .doOnSubscribe(disposable -> {
                    // 显示进度条
                    mRootView.showLoading();
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    // 隐藏进度条
                    mRootView.hideLoading();
                })
                // 使用 Rxlifecycle,使 Disposable 和 Actvity 一起销毁
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<GetAppVersionResponse>(mErrorHandler) {
                    @Override
                    public void onNext(GetAppVersionResponse response) {
                        if (null == response) {
                            return;
                        }
                        if (response.isSuccess()) {
                            mRootView.updateAppVersion(response.getBody());
                        } else {
                            mRootView.showMessage(response.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t);
                    }
                });
    }

    public void login(LoginRequest request) {
        mModel.login(request.getUsername(), request.getPassword())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(Api.MAX_RETRIES, Api.RETRY_DELAY_SECOND))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<LoginResponse>(mErrorHandler) {
                    @Override
                    public void onNext(LoginResponse response) {
                        if (null == response) {
                            ToastUtils.showShort(R.string.server_response_exception);
                            AppLifecyclesImpl.playSound();
                            return;
                        }
                        if (response.isSuccess()) {
                            // 保存用户信息
                            SPUtils.getInstance().put(Constants.USER_SESSION_ID, response.getBody().getJSESSIONID());
                            SPUtils.getInstance().put(Constants.USER_NAME, response.getBody().getUsername());
                            mRootView.goToMainActivity();
                            AppLifecyclesImpl.playSuccessSound();
                        } else {
                            mRootView.showMessage(response.getMsg());
                            AppLifecyclesImpl.playSound();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        AppLifecyclesImpl.playSound();
                    }
                });
    }
}