package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck2Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskCheck2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskCheck2Module {
    private final ScanTaskCheck2Contract.View view;

    public ScanTaskCheck2Module(ScanTaskCheck2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck2Contract.View provideScanTaskCheck2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheck2Contract.Model provideScanTaskCheck2Model(ScanTaskCheck2Model model) {
        return model;
    }
}