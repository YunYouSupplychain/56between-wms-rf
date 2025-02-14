package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.CheckContract;
import com.yunyou.androidwmsrf.mvp.model.CheckModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CheckModule {
    private final CheckContract.View view;

    public CheckModule(CheckContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CheckContract.View provideCheckView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CheckContract.Model provideCheckModel(CheckModel model) {
        return model;
    }
}