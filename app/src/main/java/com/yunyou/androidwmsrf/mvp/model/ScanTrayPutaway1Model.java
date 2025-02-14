package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayPutaway1Contract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTraceIdRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTraceIdResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class ScanTrayPutaway1Model extends BaseModel implements ScanTrayPutaway1Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ScanTrayPutaway1Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<QueryPutAwayTaskByTraceIdResponse> queryPutAwayTaskByTraceId(QueryPutAwayTaskByTraceIdRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .queryPutAwayTaskByTraceId(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }
}