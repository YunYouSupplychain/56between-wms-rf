package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskCheck1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskCheck1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskCheck1Module.class, dependencies = AppComponent.class)
public interface ScanTaskCheck1Component {
    void inject(ScanTaskCheck1Activity activity);
}