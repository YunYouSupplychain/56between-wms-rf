package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ShipmentByTraceIdModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ShipmentByTraceIdActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ShipmentByTraceIdModule.class, dependencies = AppComponent.class)
public interface ShipmentByTraceIdComponent {
    void inject(ShipmentByTraceIdActivity activity);
}