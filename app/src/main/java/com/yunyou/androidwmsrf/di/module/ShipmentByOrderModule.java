package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ShipmentByOrderContract;
import com.yunyou.androidwmsrf.mvp.model.ShipmentByOrderModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ShipmentByOrderModule {
    private final ShipmentByOrderContract.View view;

    public ShipmentByOrderModule(ShipmentByOrderContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ShipmentByOrderContract.View provideShipmentByOrderView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ShipmentByOrderContract.Model provideShipmentByOrderModel(ShipmentByOrderModel model) {
        return model;
    }
}