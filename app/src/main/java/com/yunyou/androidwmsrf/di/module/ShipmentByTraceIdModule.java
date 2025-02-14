package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ShipmentByTraceIdContract;
import com.yunyou.androidwmsrf.mvp.model.ShipmentByTraceIdModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ShipmentByTraceIdModule {
    private final ShipmentByTraceIdContract.View view;

    public ShipmentByTraceIdModule(ShipmentByTraceIdContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ShipmentByTraceIdContract.View provideShipmentByTraceIdView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ShipmentByTraceIdContract.Model provideShipmentByTraceIdModel(ShipmentByTraceIdModel model) {
        return model;
    }
}