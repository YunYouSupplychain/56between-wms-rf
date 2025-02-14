package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanRpTask2Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanRpTask2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanRpTask2Module {
    private final ScanRpTask2Contract.View view;

    public ScanRpTask2Module(ScanRpTask2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanRpTask2Contract.View provideScanRpTask2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanRpTask2Contract.Model provideScanRpTask2Model(ScanRpTask2Model model) {
        return model;
    }
}