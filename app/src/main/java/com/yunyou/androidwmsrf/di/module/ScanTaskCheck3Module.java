package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck3Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskCheck3Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskCheck3Module {
    private final ScanTaskCheck3Contract.View view;

    public ScanTaskCheck3Module(ScanTaskCheck3Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck3Contract.View provideScanTaskCheck3View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck3Contract.Model provideScanTaskCheck3Model(ScanTaskCheck3Model model) {
        return model;
    }
}