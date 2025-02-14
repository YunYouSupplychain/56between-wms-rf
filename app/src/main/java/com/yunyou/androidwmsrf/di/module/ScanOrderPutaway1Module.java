package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanOrderPutaway1Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanOrderPutaway1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanOrderPutaway1Module {
    private final ScanOrderPutaway1Contract.View view;

    public ScanOrderPutaway1Module(ScanOrderPutaway1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanOrderPutaway1Contract.View provideScanOrderPutaway1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanOrderPutaway1Contract.Model provideScanOrderPutaway1Model(ScanOrderPutaway1Model model) {
        return model;
    }
}