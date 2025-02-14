package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.InvQuery1Contract;
import com.yunyou.androidwmsrf.mvp.model.InvQuery1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InvQuery1Module {
    private final InvQuery1Contract.View view;

    public InvQuery1Module(InvQuery1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    InvQuery1Contract.View provideInvQuery1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    InvQuery1Contract.Model provideInvQuery1Model(InvQuery1Model model) {
        return model;
    }
}