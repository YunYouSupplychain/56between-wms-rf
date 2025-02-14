package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTrayPutaway1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTrayPutaway1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTrayPutaway1Module.class, dependencies = AppComponent.class)
public interface ScanTrayPutaway1Component {
    void inject(ScanTrayPutaway1Activity activity);
}