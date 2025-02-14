package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ReplenishmentContract;
import com.yunyou.androidwmsrf.mvp.model.ReplenishmentModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ReplenishmentModule {
    private final ReplenishmentContract.View view;

    public ReplenishmentModule(ReplenishmentContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ReplenishmentContract.View provideReplenishmentView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ReplenishmentContract.Model provideReplenishmentModel(ReplenishmentModel model) {
        return model;
    }
}