package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ShipmentByTrackingNoModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ShipmentByTrackingNoActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ShipmentByTrackingNoModule.class, dependencies = AppComponent.class)
public interface ShipmentByTrackingNoComponent {
    void inject(ShipmentByTrackingNoActivity activity);
}