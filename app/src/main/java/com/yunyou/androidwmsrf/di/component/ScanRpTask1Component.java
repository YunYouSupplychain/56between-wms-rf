package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanRpTask1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanRpTask1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanRpTask1Module.class, dependencies = AppComponent.class)
public interface ScanRpTask1Component {
    void inject(ScanRpTask1Activity activity);
}