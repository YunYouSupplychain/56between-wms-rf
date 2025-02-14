package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayReceipt3Contract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveAsnDetailByTraceIdRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveAsnDetailByTraceIdResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class ScanTrayReceipt3Model extends BaseModel implements ScanTrayReceipt3Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ScanTrayReceipt3Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<SaveAsnDetailByTraceIdResponse> saveAsnDetailByTraceId(SaveAsnDetailByTraceIdRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .saveAsnDetailByTraceId(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }

}