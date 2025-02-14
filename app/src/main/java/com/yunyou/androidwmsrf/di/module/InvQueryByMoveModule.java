package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InvQueryByMoveContract;
import com.yunyou.androidwmsrf.mvp.model.InvQueryByMoveModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InvQueryByMoveModule {
    private final InvQueryByMoveContract.View view;

    public InvQueryByMoveModule(InvQueryByMoveContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InvQueryByMoveContract.View provideInvQueryByMoveView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InvQueryByMoveContract.Model provideInvQueryByMoveModel(InvQueryByMoveModel model) {
        return model;
    }
}