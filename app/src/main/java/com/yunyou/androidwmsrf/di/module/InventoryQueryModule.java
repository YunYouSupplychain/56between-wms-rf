package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InventoryQueryContract;
import com.yunyou.androidwmsrf.mvp.model.InventoryQueryModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InventoryQueryModule {
    private final InventoryQueryContract.View view;

    public InventoryQueryModule(InventoryQueryContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InventoryQueryContract.View provideInventoryQueryView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InventoryQueryContract.Model provideInventoryQueryModel(InventoryQueryModel model) {
        return model;
    }
}