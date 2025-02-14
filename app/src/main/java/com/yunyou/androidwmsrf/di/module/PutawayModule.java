package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PutawayContract;
import com.yunyou.androidwmsrf.mvp.model.PutawayModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PutawayModule {
    private final PutawayContract.View view;

    public PutawayModule(PutawayContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PutawayContract.View providePutawayView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PutawayContract.Model providePutawayModel(PutawayModel model) {
        return model;
    }
}