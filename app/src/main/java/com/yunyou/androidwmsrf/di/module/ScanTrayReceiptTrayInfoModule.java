package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayReceiptTrayInfoContract;
import com.yunyou.androidwmsrf.mvp.model.ScanTrayReceiptTrayInfoModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTrayReceiptTrayInfoModule {
    private final ScanTrayReceiptTrayInfoContract.View view;

    public ScanTrayReceiptTrayInfoModule(ScanTrayReceiptTrayInfoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceiptTrayInfoContract.View provideScanTrayReceiptTrayInfoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTrayReceiptTrayInfoContract.Model provideScanTrayReceiptTrayInfoModel(ScanTrayReceiptTrayInfoModel model) {
        return model;
    }
}