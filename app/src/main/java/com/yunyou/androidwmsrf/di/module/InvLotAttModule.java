package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InvLotAttContract;
import com.yunyou.androidwmsrf.mvp.model.InvLotAttModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InvLotAttModule {
    private final InvLotAttContract.View view;

    public InvLotAttModule(InvLotAttContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InvLotAttContract.View provideInvLotAttView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InvLotAttContract.Model provideInvLotAttModel(InvLotAttModel model) {
        return model;
    }
}