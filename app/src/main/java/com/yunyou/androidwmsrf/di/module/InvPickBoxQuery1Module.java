package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InvPickBoxQuery1Contract;
import com.yunyou.androidwmsrf.mvp.model.InvPickBoxQuery1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InvPickBoxQuery1Module {
    private final InvPickBoxQuery1Contract.View view;

    public InvPickBoxQuery1Module(InvPickBoxQuery1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InvPickBoxQuery1Contract.View provideInvPickBoxQuery1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InvPickBoxQuery1Contract.Model provideInvPickBoxQuery1Model(InvPickBoxQuery1Model model) {
        return model;
    }
}