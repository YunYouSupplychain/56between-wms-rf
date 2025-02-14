package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanRpTask2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanRpTask2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanRpTask2Module.class, dependencies = AppComponent.class)
public interface ScanRpTask2Component {
    void inject(ScanRpTask2Activity activity);
}