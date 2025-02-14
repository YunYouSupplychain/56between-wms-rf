package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskPutaway1Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskPutaway1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskPutaway1Module {
    private final ScanTaskPutaway1Contract.View view;

    public ScanTaskPutaway1Module(ScanTaskPutaway1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskPutaway1Contract.View provideScanTaskPutaway1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskPutaway1Contract.Model provideScanTaskPutaway1Model(ScanTaskPutaway1Model model) {
        return model;
    }
}