package com.yunyou.androidwmsrf.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.yunyou.androidwmsrf.mvp.contract.SetServerContract;
import com.yunyou.androidwmsrf.mvp.model.SetServerModel;


@Module
public class SetServerModule {
    private final SetServerContract.View view;

    public SetServerModule(SetServerContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SetServerContract.View provideSetServerView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SetServerContract.Model provideSetServerModel(SetServerModel model) {
        return model;
    }
}