package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingByTraceIdModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingByTraceIdActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingByTraceIdModule.class, dependencies = AppComponent.class)
public interface PickingByTraceIdComponent {
    void inject(PickingByTraceIdActivity activity);
}