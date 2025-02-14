package com.yunyou.androidwmsrf.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.yunyou.androidwmsrf.mvp.contract.GetFrameUnBindContract;
import com.yunyou.androidwmsrf.mvp.model.GetFrameUnBindModel;


@Module
public class GetFrameUnBindModule {
    private final GetFrameUnBindContract.View view;

    public GetFrameUnBindModule(GetFrameUnBindContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GetFrameUnBindContract.View provideGetFrameUnBindView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GetFrameUnBindContract.Model provideGetFrameUnBindModel(GetFrameUnBindModel model) {
        return model;
    }
}