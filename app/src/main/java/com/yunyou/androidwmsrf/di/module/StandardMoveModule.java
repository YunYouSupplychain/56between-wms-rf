package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.StandardMoveContract;
import com.yunyou.androidwmsrf.mvp.model.StandardMoveModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class StandardMoveModule {
    private final StandardMoveContract.View view;

    public StandardMoveModule(StandardMoveContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    StandardMoveContract.View provideStandardMoveView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    StandardMoveContract.Model provideStandardMoveModel(StandardMoveModel model) {
        return model;
    }
}