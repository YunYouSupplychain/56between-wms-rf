package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck5Contract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryOwnerRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryOwnerResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class ScanTaskCheck5Model extends BaseModel implements ScanTaskCheck5Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ScanTaskCheck5Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<QueryOwnerResponse> queryOwner(QueryOwnerRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .queryOwner(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }
}