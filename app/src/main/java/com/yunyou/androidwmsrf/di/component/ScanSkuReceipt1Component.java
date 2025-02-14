package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanSkuReceipt1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanSkuReceipt1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanSkuReceipt1Module.class, dependencies = AppComponent.class)
public interface ScanSkuReceipt1Component {
    void inject(ScanSkuReceipt1Activity activity);
}