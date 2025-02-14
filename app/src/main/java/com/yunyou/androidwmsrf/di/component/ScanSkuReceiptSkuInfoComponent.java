package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanSkuReceiptSkuInfoModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanSkuReceiptSkuInfoActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanSkuReceiptSkuInfoModule.class, dependencies = AppComponent.class)
public interface ScanSkuReceiptSkuInfoComponent {
    void inject(ScanSkuReceiptSkuInfoActivity activity);
}