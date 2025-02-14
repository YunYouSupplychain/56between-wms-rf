package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanOrderPutaway1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanOrderPutaway1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanOrderPutaway1Module.class, dependencies = AppComponent.class)
public interface ScanOrderPutaway1Component {
    void inject(ScanOrderPutaway1Activity activity);
}