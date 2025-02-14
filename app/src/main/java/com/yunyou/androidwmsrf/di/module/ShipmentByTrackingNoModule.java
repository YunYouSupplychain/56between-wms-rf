package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ShipmentByTrackingNoContract;
import com.yunyou.androidwmsrf.mvp.model.ShipmentByTrackingNoModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ShipmentByTrackingNoModule {
    private final ShipmentByTrackingNoContract.View view;

    public ShipmentByTrackingNoModule(ShipmentByTrackingNoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ShipmentByTrackingNoContract.View provideShipmentByTrackingNoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ShipmentByTrackingNoContract.Model provideShipmentByTrackingNoModel(ShipmentByTrackingNoModel model) {
        return model;
    }
}