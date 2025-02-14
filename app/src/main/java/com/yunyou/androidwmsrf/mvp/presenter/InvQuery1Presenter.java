package com.yunyou.androidwmsrf.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.mvp.contract.InvQuery1Contract;
import com.yunyou.androidwmsrf.mvp.model.api.Api;
import com.yunyou.androidwmsrf.mvp.model.webservice.QuerySkuInvRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QuerySkuInvResponse;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class InvQuery1Presenter extends BasePresenter<InvQuery1Contract.Model, InvQuery1Contract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public InvQuery1Presenter(InvQuery1Contract.Model model, InvQuery1Contract.View rootView) {
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

    public void querySkuInv(QuerySkuInvRequest request) {
        mModel.querySkuInv(request)
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
                .subscribe(new ErrorHandleSubscriber<QuerySkuInvResponse>(mErrorHandler) {
                    @Override
                    public void onNext(QuerySkuInvResponse response) {
                        if (null == response) {
                            ToastUtils.showShort(R.string.server_response_exception);
                            AppLifecyclesImpl.playSound();
                            return;
                        }
                        if (response.isSuccess()) {
                            mRootView.goToInvQuery2Activity(response.getBody());
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