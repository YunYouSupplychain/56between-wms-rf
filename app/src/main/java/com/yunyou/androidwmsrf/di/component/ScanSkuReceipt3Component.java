package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanSkuReceipt3Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanSkuReceipt3Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanSkuReceipt3Module.class, dependencies = AppComponent.class)
public interface ScanSkuReceipt3Component {
    void inject(ScanSkuReceipt3Activity activity);
}