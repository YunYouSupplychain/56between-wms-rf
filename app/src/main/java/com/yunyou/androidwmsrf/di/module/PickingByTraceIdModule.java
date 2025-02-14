package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingByTraceIdContract;
import com.yunyou.androidwmsrf.mvp.model.PickingByTraceIdModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingByTraceIdModule {
    private final PickingByTraceIdContract.View view;

    public PickingByTraceIdModule(PickingByTraceIdContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingByTraceIdContract.View providePickingByTraceIdView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingByTraceIdContract.Model providePickingByTraceIdModel(PickingByTraceIdModel model) {
        return model;
    }
}