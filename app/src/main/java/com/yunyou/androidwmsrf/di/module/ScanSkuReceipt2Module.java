package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceipt2Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanSkuReceipt2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanSkuReceipt2Module {
    private final ScanSkuReceipt2Contract.View view;

    public ScanSkuReceipt2Module(ScanSkuReceipt2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceipt2Contract.View provideScanSkuReceipt2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanSkuReceipt2Contract.Model provideScanSkuReceipt2Model(ScanSkuReceipt2Model model) {
        return model;
    }
}