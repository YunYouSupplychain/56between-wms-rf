package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InvPickBoxQuery2Contract;
import com.yunyou.androidwmsrf.mvp.model.InvPickBoxQuery2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InvPickBoxQuery2Module {
    private final InvPickBoxQuery2Contract.View view;

    public InvPickBoxQuery2Module(InvPickBoxQuery2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InvPickBoxQuery2Contract.View provideInvPickBoxQuery2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InvPickBoxQuery2Contract.Model provideInvPickBoxQuery2Model(InvPickBoxQuery2Model model) {
        return model;
    }
}