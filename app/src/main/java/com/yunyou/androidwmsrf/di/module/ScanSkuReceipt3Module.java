package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceipt3Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanSkuReceipt3Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanSkuReceipt3Module {
    private final ScanSkuReceipt3Contract.View view;

    public ScanSkuReceipt3Module(ScanSkuReceipt3Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceipt3Contract.View provideScanSkuReceipt3View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceipt3Contract.Model provideScanSkuReceipt3Model(ScanSkuReceipt3Model model) {
        return model;
    }
}