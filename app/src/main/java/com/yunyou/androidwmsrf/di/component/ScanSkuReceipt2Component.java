package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanSkuReceipt2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanSkuReceipt2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanSkuReceipt2Module.class, dependencies = AppComponent.class)
public interface ScanSkuReceipt2Component {
    void inject(ScanSkuReceipt2Activity activity);
}