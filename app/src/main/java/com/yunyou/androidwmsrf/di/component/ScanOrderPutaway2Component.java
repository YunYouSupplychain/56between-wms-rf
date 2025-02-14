package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanOrderPutaway2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanOrderPutaway2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanOrderPutaway2Module.class, dependencies = AppComponent.class)
public interface ScanOrderPutaway2Component {
    void inject(ScanOrderPutaway2Activity activity);
}