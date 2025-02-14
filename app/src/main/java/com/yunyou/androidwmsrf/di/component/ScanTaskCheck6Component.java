package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskCheck6Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskCheck6Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskCheck6Module.class, dependencies = AppComponent.class)
public interface ScanTaskCheck6Component {
    void inject(ScanTaskCheck6Activity activity);
}