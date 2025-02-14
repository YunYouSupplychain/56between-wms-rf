package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickLotAttContract;
import com.yunyou.androidwmsrf.mvp.model.PickLotAttModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickLotAttModule {
    private final PickLotAttContract.View view;

    public PickLotAttModule(PickLotAttContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickLotAttContract.View providePickLotAttView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickLotAttContract.Model providePickLotAttModel(PickLotAttModel model) {
        return model;
    }
}