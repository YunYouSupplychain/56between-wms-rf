package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ShipmentByOrderModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ShipmentByOrderActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ShipmentByOrderModule.class, dependencies = AppComponent.class)
public interface ShipmentByOrderComponent {
    void inject(ShipmentByOrderActivity activity);
}