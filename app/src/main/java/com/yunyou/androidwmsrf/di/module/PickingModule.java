package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingContract;
import com.yunyou.androidwmsrf.mvp.model.PickingModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingModule {
    private final PickingContract.View view;

    public PickingModule(PickingContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingContract.View providePickingView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingContract.Model providePickingModel(PickingModel model) {
        return model;
    }
}