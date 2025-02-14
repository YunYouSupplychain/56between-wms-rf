package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskPutaway2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskPutaway2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskPutaway2Module.class, dependencies = AppComponent.class)
public interface ScanTaskPutaway2Component {
    void inject(ScanTaskPutaway2Activity activity);
}