package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.WholeTrayMoveContract;
import com.yunyou.androidwmsrf.mvp.model.WholeTrayMoveModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class WholeTrayMoveModule {
    private final WholeTrayMoveContract.View view;

    public WholeTrayMoveModule(WholeTrayMoveContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WholeTrayMoveContract.View provideWholeTrayMoveView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WholeTrayMoveContract.Model provideWholeTrayMoveModel(WholeTrayMoveModel model) {
        return model;
    }
}