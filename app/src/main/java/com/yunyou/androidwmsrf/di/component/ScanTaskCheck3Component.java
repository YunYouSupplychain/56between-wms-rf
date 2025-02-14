package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskCheck3Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskCheck3Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskCheck3Module.class, dependencies = AppComponent.class)
public interface ScanTaskCheck3Component {
    void inject(ScanTaskCheck3Activity activity);
}