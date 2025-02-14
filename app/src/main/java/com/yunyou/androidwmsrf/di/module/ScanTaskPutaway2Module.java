package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskPutaway2Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskPutaway2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskPutaway2Module {
    private final ScanTaskPutaway2Contract.View view;

    public ScanTaskPutaway2Module(ScanTaskPutaway2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskPutaway2Contract.View provideScanTaskPutaway2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskPutaway2Contract.Model provideScanTaskPutaway2Model(ScanTaskPutaway2Model model) {
        return model;
    }
}