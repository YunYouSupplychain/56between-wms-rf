package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayReceipt1Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTrayReceipt1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTrayReceipt1Module {
    private final ScanTrayReceipt1Contract.View view;

    public ScanTrayReceipt1Module(ScanTrayReceipt1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceipt1Contract.View provideScanTrayReceipt1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceipt1Contract.Model provideScanTrayReceipt1Model(ScanTrayReceipt1Model model) {
        return model;
    }
}