package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskCheck5Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskCheck5Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskCheck5Module.class, dependencies = AppComponent.class)
public interface ScanTaskCheck5Component {
    void inject(ScanTaskCheck5Activity activity);
}