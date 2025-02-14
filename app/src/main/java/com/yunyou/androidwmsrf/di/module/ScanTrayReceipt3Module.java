package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayReceipt3Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTrayReceipt3Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTrayReceipt3Module {
    private final ScanTrayReceipt3Contract.View view;

    public ScanTrayReceipt3Module(ScanTrayReceipt3Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceipt3Contract.View provideScanTrayReceipt3View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceipt3Contract.Model provideScanTrayReceipt3Model(ScanTrayReceipt3Model model) {
        return model;
    }
}