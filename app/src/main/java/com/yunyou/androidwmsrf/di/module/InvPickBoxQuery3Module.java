package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InvPickBoxQuery3Contract;
import com.yunyou.androidwmsrf.mvp.model.InvPickBoxQuery3Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InvPickBoxQuery3Module {
    private final InvPickBoxQuery3Contract.View view;

    public InvPickBoxQuery3Module(InvPickBoxQuery3Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InvPickBoxQuery3Contract.View provideInvPickBoxQuery3View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InvPickBoxQuery3Contract.Model provideInvPickBoxQuery3Model(InvPickBoxQuery3Model model) {
        return model;
    }
}