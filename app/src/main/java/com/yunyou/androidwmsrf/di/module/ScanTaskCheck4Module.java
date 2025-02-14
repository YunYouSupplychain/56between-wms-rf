package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck4Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskCheck4Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskCheck4Module {
    private final ScanTaskCheck4Contract.View view;

    public ScanTaskCheck4Module(ScanTaskCheck4Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck4Contract.View provideScanTaskCheck4View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck4Contract.Model provideScanTaskCheck4Model(ScanTaskCheck4Model model) {
        return model;
    }
}