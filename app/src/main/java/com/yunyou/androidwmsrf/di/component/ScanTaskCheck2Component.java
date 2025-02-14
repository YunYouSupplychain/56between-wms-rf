package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskCheck2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskCheck2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskCheck2Module.class, dependencies = AppComponent.class)
public interface ScanTaskCheck2Component {
    void inject(ScanTaskCheck2Activity activity);
}