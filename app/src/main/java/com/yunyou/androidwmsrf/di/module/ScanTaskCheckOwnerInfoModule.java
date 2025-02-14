package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheckOwnerInfoContract;
import com.yunyou.androidwmsrf.mvp.model.ScanTaskCheckOwnerInfoModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTaskCheckOwnerInfoModule {
    private final ScanTaskCheckOwnerInfoContract.View view;

    public ScanTaskCheckOwnerInfoModule(ScanTaskCheckOwnerInfoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheckOwnerInfoContract.View provideScanTaskCheckOwnerInfoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTaskCheckOwnerInfoContract.Model provideScanTaskCheckOwnerInfoModel(ScanTaskCheckOwnerInfoModel model) {
        return model;
    }
}