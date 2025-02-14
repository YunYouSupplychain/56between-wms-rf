package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.model.webservice.GetAppVersionResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.LoginResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yunyou.androidwmsrf.mvp.contract.LoginContract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<GetAppVersionResponse> getAppVersion(String appId) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .getAppVersion(appId))
                // 不使用缓存
                .flatMap(observable -> observable);
    }

    @Override
    public Observable<LoginResponse> login(String username, String password) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .login(username, password)).flatMap(observable -> observable);
    }
}