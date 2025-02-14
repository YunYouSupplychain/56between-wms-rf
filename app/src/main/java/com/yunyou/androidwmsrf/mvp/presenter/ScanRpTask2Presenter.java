package com.yunyou.androidwmsrf.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.mvp.contract.ScanRpTask2Contract;
import com.yunyou.androidwmsrf.mvp.model.api.Api;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveReplenishmentRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveReplenishmentResponse;
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
public class ScanRpTask2Presenter extends BasePresenter<ScanRpTask2Contract.Model, ScanRpTask2Contract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ScanRpTask2Presenter(ScanRpTask2Contract.Model model, ScanRpTask2Contract.View rootView) {
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

    public void saveReplenishment(SaveReplenishmentRequest request) {
        mModel.saveReplenishment(request)
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
                .subscribe(new ErrorHandleSubscriber<SaveReplenishmentResponse>(mErrorHandler) {
                    @Override
                    public void onNext(SaveReplenishmentResponse response) {
                        if (null == response) {
                            ToastUtils.showShort(R.string.server_response_exception);
                            AppLifecyclesImpl.playSound();
                            return;
                        }
                        if (response.isSuccess()) {
                            mRootView.showMessage(response.getMsg());
                            mRootView.afterSave();
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