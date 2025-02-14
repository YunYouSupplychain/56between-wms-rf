package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayReceipt2Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTrayReceipt2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTrayReceipt2Module {
    private final ScanTrayReceipt2Contract.View view;

    public ScanTrayReceipt2Module(ScanTrayReceipt2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceipt2Contract.View provideScanTrayReceipt2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceipt2Contract.Model provideScanTrayReceipt2Model(ScanTrayReceipt2Model model) {
        return model;
    }
}