package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck1Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskCheck1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskCheck1Module {
    private final ScanTaskCheck1Contract.View view;

    public ScanTaskCheck1Module(ScanTaskCheck1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck1Contract.View provideScanTaskCheck1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck1Contract.Model provideScanTaskCheck1Model(ScanTaskCheck1Model model) {
        return model;
    }
}