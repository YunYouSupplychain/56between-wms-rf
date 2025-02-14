package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.WholeTrayMoveContract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class WholeTrayMoveModel extends BaseModel implements WholeTrayMoveContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WholeTrayMoveModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<QueryMovementResponse> queryMovement(QueryMovementRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .queryMovement(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }

    @Override
    public Observable<SaveMovementResponse> saveMovement(List<SaveMovementRequest> request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .saveMovement(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }
}