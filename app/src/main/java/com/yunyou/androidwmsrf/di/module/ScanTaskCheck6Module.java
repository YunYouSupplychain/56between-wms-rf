package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck6Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskCheck6Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskCheck6Module {
    private final ScanTaskCheck6Contract.View view;

    public ScanTaskCheck6Module(ScanTaskCheck6Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck6Contract.View provideScanTaskCheck6View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck6Contract.Model provideScanTaskCheck6Model(ScanTaskCheck6Model model) {
        return model;
    }
}