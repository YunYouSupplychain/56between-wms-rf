package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskPutaway1Contract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTaskNoRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTaskNoResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class ScanTaskPutaway1Model extends BaseModel implements ScanTaskPutaway1Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ScanTaskPutaway1Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<QueryPutAwayTaskByTaskNoResponse> queryPutAwayTaskByTaskNo(QueryPutAwayTaskByTaskNoRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .queryPutAwayTaskByTaskNo(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }
}