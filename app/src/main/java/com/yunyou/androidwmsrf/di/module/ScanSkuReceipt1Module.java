package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceipt1Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanSkuReceipt1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanSkuReceipt1Module {
    private final ScanSkuReceipt1Contract.View view;

    public ScanSkuReceipt1Module(ScanSkuReceipt1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceipt1Contract.View provideScanSkuReceipt1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceipt1Contract.Model provideScanSkuReceipt1Model(ScanSkuReceipt1Model model) {
        return model;
    }
}