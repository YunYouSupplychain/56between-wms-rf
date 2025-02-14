package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ShipmentContract;
import com.yunyou.androidwmsrf.mvp.model.ShipmentModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ShipmentModule {
    private final ShipmentContract.View view;

    public ShipmentModule(ShipmentContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ShipmentContract.View provideShipmentView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ShipmentContract.Model provideShipmentModel(ShipmentModel model) {
        return model;
    }
}