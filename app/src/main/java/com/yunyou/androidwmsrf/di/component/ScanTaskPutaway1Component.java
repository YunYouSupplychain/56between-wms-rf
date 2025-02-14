package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskPutaway1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskPutaway1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskPutaway1Module.class, dependencies = AppComponent.class)
public interface ScanTaskPutaway1Component {
    void inject(ScanTaskPutaway1Activity activity);
}