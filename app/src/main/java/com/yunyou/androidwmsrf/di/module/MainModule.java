package com.yunyou.androidwmsrf.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.yunyou.androidwmsrf.mvp.contract.MainContract;
import com.yunyou.androidwmsrf.mvp.model.MainModel;


@Module
public class MainModule {
    private final MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainContract.Model provideMainModel(MainModel model) {
        return model;
    }
}