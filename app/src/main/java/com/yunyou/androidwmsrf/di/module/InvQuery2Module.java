package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InvQuery2Contract;
import com.yunyou.androidwmsrf.mvp.model.InvQuery2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InvQuery2Module {
    private final InvQuery2Contract.View view;

    public InvQuery2Module(InvQuery2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InvQuery2Contract.View provideInvQuery2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InvQuery2Contract.Model provideInvQuery2Model(InvQuery2Model model) {
        return model;
    }
}