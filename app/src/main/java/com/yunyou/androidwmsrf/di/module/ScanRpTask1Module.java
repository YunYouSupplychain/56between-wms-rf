package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanRpTask1Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanRpTask1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanRpTask1Module {
    private final ScanRpTask1Contract.View view;

    public ScanRpTask1Module(ScanRpTask1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanRpTask1Contract.View provideScanRpTask1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanRpTask1Contract.Model provideRpTask1Model(ScanRpTask1Model model) {
        return model;
    }
}