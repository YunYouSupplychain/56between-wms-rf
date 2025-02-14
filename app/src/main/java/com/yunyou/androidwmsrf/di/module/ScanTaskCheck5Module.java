package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck5Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskCheck5Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskCheck5Module {
    private final ScanTaskCheck5Contract.View view;

    public ScanTaskCheck5Module(ScanTaskCheck5Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck5Contract.View provideScanTaskCheck5View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck5Contract.Model provideScanTaskCheck5Model(ScanTaskCheck5Model model) {
        return model;
    }
}