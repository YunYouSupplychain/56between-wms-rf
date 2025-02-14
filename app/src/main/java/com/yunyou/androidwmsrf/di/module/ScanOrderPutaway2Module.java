package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanOrderPutaway2Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanOrderPutaway2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanOrderPutaway2Module {
    private final ScanOrderPutaway2Contract.View view;

    public ScanOrderPutaway2Module(ScanOrderPutaway2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanOrderPutaway2Contract.View provideScanOrderPutaway2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanOrderPutaway2Contract.Model provideScanOrderPutaway2Model(ScanOrderPutaway2Model model) {
        return model;
    }
}