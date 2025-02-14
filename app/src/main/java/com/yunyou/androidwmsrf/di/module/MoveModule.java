package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.MoveContract;
import com.yunyou.androidwmsrf.mvp.model.MoveModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MoveModule {
    private final MoveContract.View view;

    public MoveModule(MoveContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MoveContract.View provideMoveView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MoveContract.Model provideMoveModel(MoveModel model) {
        return model;
    }
}