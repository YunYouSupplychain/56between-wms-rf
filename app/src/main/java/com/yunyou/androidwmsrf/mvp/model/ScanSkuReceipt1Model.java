package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceipt1Contract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.CheckAsnIsPalletizeRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.CheckAsnIsPalletizeResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class ScanSkuReceipt1Model extends BaseModel implements ScanSkuReceipt1Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ScanSkuReceipt1Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<CheckAsnIsPalletizeResponse> checkAsnIsPalletize(CheckAsnIsPalletizeRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .checkAsnIsPalletize(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }
}