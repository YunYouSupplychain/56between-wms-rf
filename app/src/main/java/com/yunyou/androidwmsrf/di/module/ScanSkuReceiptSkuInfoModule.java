package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceiptSkuInfoContract;
import com.yunyou.androidwmsrf.mvp.model.ScanSkuReceiptSkuInfoModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanSkuReceiptSkuInfoModule {
    private final ScanSkuReceiptSkuInfoContract.View view;

    public ScanSkuReceiptSkuInfoModule(ScanSkuReceiptSkuInfoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceiptSkuInfoContract.View provideScanSkuReceiptSkuInfoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceiptSkuInfoContract.Model provideScanSkuReceiptSkuInfoModel(ScanSkuReceiptSkuInfoModel model) {
        return model;
    }
}